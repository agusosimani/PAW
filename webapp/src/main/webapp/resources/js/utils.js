function add_rate(clicked_id) {
    let searchParams = new URLSearchParams(window.location.search);
    //$.post("/rate_recipe", {rate: clicked_id, recipeId: searchParams.get("recipeId")});
    $.ajax({
        url: "/rate_recipe",
        data: {rate: clicked_id, recipeId: searchParams.get("recipeId")},
        type: "POST",
        complete: function () {
            window.location.reload();
        }
    });
};

$(function () {
    $('#btnAdd_1').click(function () {
        var num = $('.clonedInput_1').length,
            newNum = new Number(num + 1),
            newElem = $('#clonedInput' + num).clone().attr('id', 'clonedInput' + newNum).fadeIn('slow');

        $('#clonedInput' + num).after(newElem);
    });

    $('#btnAdd_2').click(function () {
        var num = $('.cloneInput_1').length,
            newNum = new Number(num + 1),
            newElem = $('#cloneInput' + num).clone().attr('id', 'cloneInput' + newNum).fadeIn('slow');

        newElem.find('.ingredientLabel').attr('for', 'ingredients[' + num + '].ingredient.id');
        newElem.find('.ingredientSelect').attr('name', 'ingredients[' + num + '].ingredient.id');
        newElem.find('.ingredientAmountLabel').attr('for', 'ingredients[' + num + '].amount');
        newElem.find('.ingredientAmountInput').attr('name', 'ingredients[' + num + '].amount');

        $('#cloneInput' + num).after(newElem);
    });

    $(window).on('load',function(){
        var win = $(this); //this = window
        if (win.width() > 768) {
            $("#filters-big-card").append($("#filters-card").show());
            console.log("asdmoaskd");
        }
        if(win.width() <= 768){
            console.log("modal");
            $("#filters-modal-body").append($("#filters-card").show());
        }
        console.log("asdmoaskd");
    });

    $(window).on('resize', function(){
        var win = $(this); //this = window
        if (win.width() > 768) {
            $("#filters-big-card").append($("#filters-card").show());
            console.log("asdmoaskd");
        }
        if(win.width() <= 768){
            console.log("modal");
            $("#filters-modal-body").append($("#filters-card").show());
        }
        console.log("asdmoaskd");
    });

});
