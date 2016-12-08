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

    autocomplete() {
        let nameRefInput = this.refs.nameRef;

        if (!nameRefInput.value.length) {
            return;
        }

        this.props.autocomplete(nameRefInput.value);
    },

    render(){

        return (
            <aside role="complementary" className="container">
                <h2>Поиск фильмов</h2>
                <div className="input-group add-on">
                    <input className="form-control" ref="nameRef" placeholder="Search" name="srch-term" id="srch-term"
                           type="text" onKeyDown={this.autocomplete}/>
                    <div className="input-group-btn">
                        <button className="btn btn-default" type="submit" onClick={this.filterFilms}>Поиск</button>
                    </div>
                </div>
            </aside>
        );
    }
});

var App = React.createClass({
    getInitialState: function () {
        return {films: []};
    },

    componentWillMount() {
        axios.get(`/all`)
            .then(res => {
                this.setState({films: res.data});
            });
    },


    filterFilms (name){
        axios.get(`/search/film?name=` + name)
            .then(res => {
                this.setState({films: res.data});
            });
    },

    autocomplete (q) {
        axios.get('/search/films', {params: {q: q}})
            .then(res => {
                if (res.data.length == 0)
                    return;

                this.setState({films: res.data});
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
                <Search filterFilms={this.filterFilms} autocomplete={this.autocomplete} key="search"/>
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