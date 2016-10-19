
var app = angular.module("movie", ['ngSanitize']);

app.controller("MovieController", function ($scope, $http) {
    
    $scope.movies = [];
    $scope.type = 'MATCH';

    $scope.add = function (movie, csrf) {
        $http.post('/api/movies/', movie).then(function () {
            $scope.movies.push(movie)
        });
    };

    $scope.search = function () {
        if (!$scope.searchInput.length){
            $scope.findAll();
            return;
        }
        if ($scope.type == 'AUTO'){
            $scope.autocomplete();
            return;
        }
        $http.get('/api/movies/search', { params: {q: $scope.searchInput, type: $scope.type, page: 1, size: 10 } })
            .then(function(resp){
                $scope.movies = resp.data;
            })
    };

    $scope.load = function (csrf) {
        $http.get('/resources/movies.json')
            .then(function(resp){
                resp.data.forEach((movie) => {
                    $scope.add(movie, csrf);
                });
            })
    }


    $scope.findAll = function () {
        $http.get('/api/movies/')
            .then(function (resp) {
                $scope.movies = resp.data._embedded.movies;
            })
    };
    
    $scope.autocomplete = function () {

        if (!$scope.searchInput.length){
            return;
        }
        $http.get('/api/movies/autocomplete', { params: {q: $scope.searchInput } })
            .then(function(resp){
                let data = JSON.parse(resp.data);
                $scope.movies = data.hits.hits.map(h => {
                    let obj = {};
                    if (h.highlight && h.highlight.hasOwnProperty("name")){
                        obj.name = h.highlight.name;
                    } else {
                        obj.name = h._source.name;
                    }

                    if (h.highlight && h.highlight.hasOwnProperty("description")){
                        obj.description = h.highlight.description;
                    } else {
                        obj.description = h._source.description;
                    }
                    return obj;
                })
            })
    };
    
    $scope.findAll();
});


