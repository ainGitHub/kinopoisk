class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            films: []
        };
    }

    componentDidMount() {
        axios.get(`/all`)
            .then(res => {
                this.setState({films: res.data});
            });
    }

    filterFilms(filtered) {
        if (filtered == null) {
            alert("aaa");
        } else {
            this.setState({films: filtered})
        }
    };

    render() {
        let films = this.state.films.map((film) => {
            return <Film film={film} key={"/film/" + film.id}/>
        });
        return (
            <div>
                <Search filterFilms={() => this.filterFilms} key="search"/>
                <article className="post content">
                    <ul className="post-list">
                        {films}
                    </ul>
                </article>
            </div>
        );
    }
}

class Film extends React.Component {
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
}

class Search extends React.Component {
    constructor(props) {
        super(props);

    }

    filterFilms() {
        alert(this);
    };

    render() {
        return (
            <aside role="complementary">
                <h2>Addtional info</h2>
                <p>Vestibulum viverra <strong>consectetur enim vel rutrum</strong>. Mauris hendrerit sodales congue.
                    Etiam
                    malesuada nibh id sapien tincidunt vitae rhoncus nunc tincidunt.</p>
                <p>Curabitur posuere libero sit amet est tristique egestas. Duis porta tempor tristique. Nam in erat sed
                    leo
                    lacinia vestibulum vitae in ipsum.</p>
                <p><a href="#">Jump now <span className="icon">:</span></a></p>
                <button onClick={() => this.filterFilms}>Hello</button>
            </aside>
        );
    }
}


ReactDOM.render(
    <App/>,
    document.getElementById('root')
);