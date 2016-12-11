var Film = React.createClass({
    render() {
        let film = this.props.film;
        let link = "/film/" + film.id;
        return (
            <li>
                <h2><a href={link}>{film.name}</a></h2>
                <img src={film.image} width="180" height="280"/>
                <p className="description">{film.description}</p>
                <p><a className="more-link" href={link}>Продолжить<span className="icon">:</span></a></p>
            </li>
        );
    }
});

var Search = React.createClass({
    filterFilms() {
        var name = this.refs.nameRef.value;
        if (name != null) {
            this.props.filterFilms(name);
        }
    },

    search(event) {
        let nameRefInput = this.refs.nameRef;

        if (!nameRefInput.value.length) {
            return;
        }
        /*  this.autocomplete(nameRefInput.value);*/

        this.props.search(nameRefInput.value);
    },

    autocomplete(q) {
        let nameRefInput = this.refs.nameRef;
        let help = this.refs.helpInput;

        if (!nameRefInput.value.length) {
            return;
        }

        axios.get('/autocomplete', {params: {q: q}})
            .then(res => {
                if (res.data.length == 0)
                    return;
                let hits = res.data.hits.hits;
                if (hits.length != 0) {
                    console.log(hits[0].highlight.name);
                    help.value = hits[0].highlight.name;
                } else {
                    nameRefInput.placeholder = "Search";
                }
            });
    },

    genreSearch(event) {
        let selVal = this.refs.select.value;
        this.props.filterByGenre(selVal);
    },

    render(){
        let genres = this.props.genres.map(genre => {
            return <option key={genre.id}>{genre.name}</option>;
        });

        return (
            <aside role="complementary" className="container">
                <h2>Поиск фильмов</h2>
                <div className="input-group add-on">
                    <input className="form-control" ref="nameRef" placeholder="Search" name="srch-term" id="srch-term"
                           type="text" onKeyDown={this.search}/>
                    <div className="input-group-btn">
                        <button className="btn btn-default" type="submit" onClick={this.filterFilms}>Поиск</button>
                    </div>
                </div>
                <br/>
                <div className="form-group">
                    <h6 for="sel1" className="text text-primary">Жанры:</h6>
                    <select ref="select" className="form-control" id="sel1" onChange={this.genreSearch}>
                        {genres}
                    </select>
                </div>
            </aside>
        );
    }
});

var App = React.createClass({
    getInitialState: function () {
        return {films: [], genres: []};
    },

    componentWillMount() {
        axios.get(`/all`)
            .then(res => {
                console.log(res);
                this.setState({films: res.data.films, genres: res.data.genres});
            });
    },


    filterFilms (name){
        axios.get(`/search/film?name=` + name)
            .then(res => {
                this.setState({films: res.data, genres: this.state.genres});
            });
    },

    search(q) {
        axios.get('/search/films', {params: {q: q}})
            .then(res => {
                if (res.data.length == 0)
                    return;

                this.setState({films: res.data, genres: this.state.genres});
            });
    },

    filterByGenre(genre){
        axios.get('/search/bygenre', {params: {genre: genre}})
            .then(res => {
                console.log(res);
                this.setState({films: res.data, genres: this.state.genres});
            });
    },

    render() {
        self = this;

        let films = this.state.films.map((film) => {
            return <Film film={film} key={"/film/" + film.id}/>
        });

        if (films.length == 0)
            films = "По вашему запросу фильмы не найдеы";
        return (
            <div>
                <Search filterByGenre={this.filterByGenre} filterFilms={this.filterFilms} search={this.search}
                        genres={this.state.genres} key={films[0].id}/>
                <article className="post content">
                    <ul className="post-list">
                        {films}
                    </ul>
                </article>
            </div>
        )
    }
});


ReactDOM.render(
    <App/>,
    document.getElementById('root')
);