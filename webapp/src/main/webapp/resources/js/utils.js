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
        var num = JSON.parse(localStorage.getItem("count_new_recipe")),
            newNum = new Number(num + 1),
            newElem = $('.to_clone:first').clone().attr('id', 'clonedInput' + newNum).fadeIn('slow');

        $('.to_clone:last').after(newElem);
        localStorage.setItem("count_new_recipe", JSON.stringify(JSON.parse(localStorage.getItem("count_new_recipe")) +1));
    });

    $('#btnAdd_3').click(function () {
        var num = JSON.parse(localStorage.getItem("count_edit_recipe")),
            newNum = new Number(num + 1),
            newElem = $('.to_clone:first').clone().attr('id', 'clonedInput' + newNum).fadeIn('slow');

        $('.to_clone:last').after(newElem);
        localStorage.setItem("count_edit_recipe", JSON.stringify(JSON.parse(localStorage.getItem("count_edit_recipe")) +1));
    });

    $('#btnAdd_2').click(function () {
        var num = JSON.parse(localStorage.getItem("count_add_ingredients")),
            newNum = new Number(num + 1),
            newElem = $('.to_clone:first').clone().attr('id', 'cloneInput' + newNum).fadeIn('slow');

        newElem.find('.ingredientLabel').attr('for', 'ingredients[' + num + '].ingredient.id');
        newElem.find('.ingredientSelect').attr('name', 'ingredients[' + num + '].ingredient.id');
        newElem.find('.ingredientAmountLabel').attr('for', 'ingredients[' + num + '].amount');
        newElem.find('.ingredientAmountInput').attr('name', 'ingredients[' + num + '].amount');

        $('.to_clone:last').after(newElem);
        localStorage.setItem("count_add_ingredients", JSON.stringify(JSON.parse(localStorage.getItem("count_add_ingredients")) +1));
    });

    $(window).on('load',function(){
        var ingredients_count = 1;
        localStorage.setItem("count_add_ingredients", JSON.stringify(ingredients_count));
        localStorage.setItem("count_new_recipe", JSON.stringify(ingredients_count));
        localStorage.setItem("count_edit_recipe", JSON.stringify(ingredients_count));
        var win = $(this); //this = window
        if (win.width() > 790) {
            $("#filters-big-card").append($("#filters-card").show());
            $("#user-big-card").append($("#user-card").show());
        }
        if(win.width() <= 790){
            $("#filters-modal-body").append($("#filters-card").show());
            $("#user-modal-body").append($("#user-card").show());
        }
    });

    $(window).on('resize', function(){
        var win = $(this); //this = window
        if (win.width() > 790) {
            $("#filters-big-card").append($("#filters-card").show());
            $("#user-big-card").append($("#user-card").show());
        }
        if(win.width() <= 790){
            console.log("modal");
            $("#filters-modal-body").append($("#filters-card").show());
            $("#user-modal-body").append($("#user-card").show());
        }
    });


});