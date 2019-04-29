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
    if ($(window).width() <= 768){

        $(".collapse").removeClass("in");

        $(".navigation").css("height" , "auto");

        $(".artist").css("height" , "auto");

    }
});

$(window).on("resize load", function(){
    if ($(window).width() > 768){

        $(".collapse").addClass("in");

        $(".navigation__list__header").click()

    }
});

// $("body").on("focus", ".mdb-autocomplete", function() {
//     var states = [
//         "Alabana",
//         "Alaska",
//         "Arizona",
//         "Arkansas",
//         "California"];
//     $(this).autocomplete({
//         source: states,
//         minLength:0,
//     });
//     return false;
// }).bind('focus', function(){ $(this).autocomplete("search"); } );