/**
 * @author Astafyev Igor
 *         11-405
 *         for DZ-labs
 */

$(document).ready(function () {

    $('#city').blur(function () {
        if ($(this).val() != '') {
            var pattern = /[а-яА-Я-]+(\s)*$/i;
            if (pattern.test($(this).val())) {
                $(this).css({'border': '1px solid #569b44'});
                $('#valid-city').text('');
            } else {
                $(this).css({'border': '1px solid #ff0000'});
                $('#valid-city').css({'color':'#ff0000'}).text('Недопустимые символы');
            }
        }
    });


    $('#email').blur(function () {
        if ($(this).val() != '') {
            var pattern = /^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$/i;
            if (pattern.test($(this).val())) {
                $(this).css({'border': '1px solid #569b44'});
                $('#valid-email').text('');
            } else {
                $(this).css({'border': '1px solid #ff0000'});
                $('#valid-email').css({'color': '#ff0000'}).text('Некорректный e-mail');
            }
        }
    });
});