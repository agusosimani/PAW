// Tooltips

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})

// Viewport Heights

$(window).on("resize load", function(){

    var totalHeight = $(window).height();

    var headerHeight = $('.header').outerHeight();
    var footerHeight = $('.current-track').outerHeight();
    var playlistHeight = $('.playlist').outerHeight();
    var nowPlaying = $('.playing').outerHeight();

    var navHeight = totalHeight - (headerHeight + footerHeight + playlistHeight + nowPlaying);
    var artistHeight = totalHeight - (headerHeight + footerHeight);

    $(".navigation").css("height" , navHeight);
    $(".artist").css("height" , artistHeight);
    $(".social").css("height" , artistHeight);

});

// Collapse Toggles

$(".navigation__list__header").on( "click" , function() {

    $(this).toggleClass( "active" );

});


// Media Queries

$(window).on("resize load", function(){
    if ($(window).width() <= 790){

        $(".collapse").removeClass("in");

        $(".navigation").css("height" , "auto");

        $(".artist").css("height" , "auto");

    }
});

$(window).on("resize load", function(){
    if ($(window).width() > 790){

        $(".collapse").addClass("in");

        $(".navigation__list__header").click()

    }
});

function delete_item_add_ingredient(button) {
    var key = "count_add_ingredients";
    if (JSON.parse(localStorage.getItem(key)) > 1) {
        $(button).parent().remove();
        localStorage.setItem(key, JSON.stringify(JSON.parse(localStorage.getItem(key)) - 1));
    }
};

function delete_item_new_recipe(button) {
    var key = "count_new_recipe";
    if (JSON.parse(localStorage.getItem(key)) > 1) {
        $(button).parent().remove();
        localStorage.setItem(key, JSON.stringify(JSON.parse(localStorage.getItem(key)) - 1));
    }
};

function delete_item_edit_recipe(button) {
    var key = "count_edit_recipe";
    if (JSON.parse(localStorage.getItem(key)) > 1) {
        $(button).parent().remove();
        localStorage.setItem(key, JSON.stringify(JSON.parse(localStorage.getItem(key)) - 1));
    }
};