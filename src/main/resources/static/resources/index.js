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
        let selVal = this.refs.select;
        if (selVal.value == 0)
            this.filterFilms();
        else
            this.props.filterByGenre(selVal.value);
    },

    max(){
        var self = this;
        var scroll_2 = this.refs.scroll_2;
        var year2 = this.refs.year2;
        var max = scroll_2.value;

        year2.textContent = max;
    },

    min(){
        var scroll_1 = this.refs.scroll_1;
        var year1 = this.refs.year1;
        var min = scroll_1.value;

        year1.textContent = min;
    },

    filterByYear(){
        var year1 = this.refs.year1.textContent;
        var year2 = this.refs.year2.textContent;

        var from, to;

        if (year1 > year2) {
            to = year1;
            from = year2;
        } else {
            to = year2;
            from = year1;
        }

        this.props.filterByYear(from, to);
    },

    sort(){
        this.props.sort();
    },

    render(){
        let genres = this.props.genres.map(genre => {
            return <option key={genre.id} value={genre.id}>{genre.name}</option>;
        });

        return (
            <aside role="complementary" className="container">
                <div className="sort-bar">
                    <button onClick={this.sort} className="btn-sm">Сортировать по году</button>
                </div>
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
                    <h6 htmlFor="sel1" className="text text-primary">Жанры:</h6>
                    <select ref="select" className="form-control" id="sel1" onChange={this.genreSearch}>
                        <option key={0} value="0">Все жанры</option>
                        {genres}
                    </select>
                </div>
                <div className="input-group add-on">
                    <label ref="year1">2000</label>
                    <br/>
                    <input className="form-control-range" type="range" min="2000" max="2018" onChange={this.min}
                           ref="scroll_1" defaultValue="2000"/>
                    <br/>
                    <input className="form-control-range" type="range" min="2000" max="2018" onChange={this.max}
                           ref="scroll_2" defaultValue="2018"/>
                    <br/>
                    <label ref="year2">2018</label>
                    <br/>
                    <div className="">
                        <input type="submit" className="btn btn-primary btn-sm" value="Искать"
                               onClick={this.filterByYear}/>
                    </div>
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
        axios.get('/search/by/genre', {params: {genre: genre}})
            .then(res => {
                console.log(res);
                this.setState({films: res.data, genres: this.state.genres});
            });
    },

    filterByYear(from, to){
        console.log(from);
        console.log(to);
        axios.get('/search/by/year', {params: {from: from, to: to}})
            .then(res => {
                console.log(res);
                this.setState({films: res.data, genres: this.state.genres});
            });
    },

    sort(){
        axios.get(`/sort`)
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
            films = "По вашему запросу фильмы не найдены";
        return (
            <div>
                <Search filterByGenre={this.filterByGenre}
                        filterFilms={this.filterFilms}
                        search={this.search}
                        genres={this.state.genres}
                        filterByYear={this.filterByYear}
                        sort={this.sort}
                        key={films[0].id}/>
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