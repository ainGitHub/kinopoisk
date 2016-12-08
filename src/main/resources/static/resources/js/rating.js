/**
 * Created by Admin on 01.12.2016.
 */

$(document).ready(function () {
    $(document).on("click", "#star-4", function () {
        event.preventDefault();
        var $this = $(this);
        ratingSave(parseInt($this.data("id"), 10), 5);
    });
});

function ratingSave(id, star) {
    $.ajax({
        type: "GET",
        url: "/film/rating/" + id + "/" + star,
        success: function (data) {
            if (data == 'good') {
                location.reload();
            }
            if (data == 'non-auto') {
                alert("Проставлять рейтинг могут только авторизованные пользователи");
            }
            if (data == 'already') {
                alert("Вы уже оставляли рейтинг к данному фильму");
            }
        },
        error: function () {
            alert("На сервере произошла ошибка. Попробуйте повторить попытку позже");
        }
    })
};

$(document).on("click", "#star-3", function () {
    event.preventDefault();
    var $this = $(this);
    $.ajax({
        type: "GET",
        url: "/film/rating/" + $this.data("id") + "/4",
        data: {id: $this.data("id")},
        success: function (data) {
            if (data == 'good') {
                location.reload();
            }
            if (data == 'non-auto') {
                alert("Проставлять рейтинг могут только авторизованные пользователи");
            }
            if (data == 'already') {
                alert("Вы уже оставляли рейтинг к данному фильму");
            }
        },
        error: function () {
            alert("На сервере произошла ошибка. Попробуйте повторить попытку позже");
        }
    });
});

$(document).on("click", "#star-2", function () {
    event.preventDefault();
    var $this = $(this);
    $.ajax({
        type: "GET",
        url: "/film/rating/" + $this.data("id") + "/3",
        data: {id: $this.data("id")},
        success: function (data) {
            if (data == 'good') {
                location.reload();
            }
            if (data == 'non-auto') {
                alert("Проставлять рейтинг могут только авторизованные пользователи");
            }
            if (data == 'already') {
                alert("Вы уже оставляли рейтинг к данному фильму");
            }
        },
        error: function () {
            alert("На сервере произошла ошибка. Попробуйте повторить попытку позже");
        }
    });
});

$(document).on("click", "#star-1", function () {
    event.preventDefault();
    var $this = $(this);
    $.ajax({
        type: "GET",
        url: "/film/rating/" + $this.data("id") + "/2",
        data: {id: $this.data("id")},
        success: function (data) {
            if (data == 'good') {
                location.reload();
            }
            if (data == 'non-auto') {
                alert("Проставлять рейтинг могут только авторизованные пользователи");
            }
            if (data == 'already') {
                alert("Вы уже оставляли рейтинг к данному фильму");
            }
        },
        error: function () {
            alert("На сервере произошла ошибка. Попробуйте повторить попытку позже");
        }
    });
});

$(document).on("click", "#star-0", function () {
    event.preventDefault();
    var $this = $(this);
    $.ajax({
        type: "GET",
        url: "/film/rating/" + $this.data("id") + "/1",
        data: {id: $this.data("id")},
        success: function (data) {
            if (data == 'good') {
                location.reload();
            }
            if (data == 'non-auto') {
                alert("Проставлять рейтинг могут только авторизованные пользователи");
            }
            if (data == 'already') {
                alert("Вы уже оставляли рейтинг к данному фильму");
            }
        },
        error: function () {
            alert("На сервере произошла ошибка. Попробуйте повторить попытку позже");
        }
    });
});
