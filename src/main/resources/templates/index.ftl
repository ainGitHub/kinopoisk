<!DOCTYPE html>
<html ng-app="movie">
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-sanitize.min.js"></script>

    <script src="/resources/js/main.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <style>
        .row {
            margin-top: 20px;
        }
    </style>
</head>
<body ng-controller="MovieController">
<div class="row">
    <div class="container">
        <div class="col-md-6 form-inline">
            <label for="search">Поиск:</label>
            <input class="form-control" ng-model="searchInput" id="search" ng-change="search()"
                   ng-model-options="{ debounce: 300 }"/>
        </div>
        <div class="col-md-3">
            <select class="form-control" ng-model="type" ng-change="search()">
                <option value="MATCH">match</option>
                <option value="MATCH_PHRASE">match phrase</option>
                <option value="MATCH_PHRASE_PREFIX">match phrase prefix</option>
                <option value="FUZZY">fuzzy</option>
                <option value="AUTO">autocomplete</option>
            </select>
        </div>
        <div class="col-md-3">
            <button ng-click="load('${_csrf.token}')" class="btn btn-md btn-default">Импортировать все фильмы</button>
        </div>

    </div>
</div>
<div class="row" >
    <div class="container">
        <table class="table table-striped col-md-6">
            <thead>
                <td>Название</td>
                <td>Описание</td>
            </thead>
            <tbody>
                <tr ng-repeat="movie in movies">
                    <td ng-bind-html="movie.name"></td>
                    <td>{{movie.description}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>