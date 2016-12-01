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

    render() {
        let films = this.state.films.map((film) => {
            return <Film film={film} key={"/film/" + film.id}/>
        });
        return (
            <div>
                <Search/>
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

        return (
            <li>
                <h2><a href={this.props.key}>{film.name}</a></h2>
                <img src={film.image} width="180" height="280"/>
                <p className="description">{film.description}</p>
                <p><a className="more-link" href={this.props.key}>Продолжить<span className="icon">:</span></a>
                </p>
            </li>
        );
    }
}

class Search extends React.Component {
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
                <p><a href="#">Jump now <span class="icon">:</span></a></p>
            </aside>
        );
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('root')
);