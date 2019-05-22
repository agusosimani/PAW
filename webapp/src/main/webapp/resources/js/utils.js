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


function delete_ingredient2(button) {
    var num = $('.cloneInput_1').length,
        newNum = new Number(num + 1);


    var i = button.id;

    i = parseInt(i) + 2;
    for (i; i <= num; i++) {
        var replaceFor = i - 1;
        var replaceIndex = replaceFor - 1;
        $('#cloneInput' + i).find('.ingredientLabel').attr('for', 'ingredients[' + replaceIndex + '].ingredient.id');
        $('#cloneInput' + i).find('.ingredientSelect').attr('name', 'ingredients[' +replaceIndex + '].ingredient.id');
        $('#cloneInput' + i).find('.ingredientAmountLabel').attr('for', 'ingredients[' + replaceIndex + '].amount');
        $('#cloneInput' + i).find('.ingredientAmountInput').attr('name', 'ingredients[' +replaceIndex + '].amount');
        $('#cloneInput' + i).find('.ingredientAmountInput').attr('name', 'ingredients[' + replaceIndex + '].amount');
        $('#cloneInput' + i).find('.delete-ingredient-button').attr('id', replaceIndex);
        $('#cloneInput' + i).attr('id', 'cloneInput' + replaceFor);
    }

    if (num > 1) {
        $(button).parent().remove();
    }
};

function delete_ingredient(button) {
    var num = $('.clonedInput_1').length,
        newNum = new Number(num + 1);


    var i = button.id;

    i = parseInt(i) + 2;
    for (i; i <= num; i++) {
        var replaceFor = i - 1;
        var replaceIndex = replaceFor - 1;
        $('#clonedInput' + i).find('.ingredientLabel').attr('for', 'ingredients[' + replaceIndex + '].ingredient.id');
        $('#clonedInput' + i).find('.ingredientSelect').attr('name', 'ingredients[' +replaceIndex + '].ingredient.id');
        $('#clonedInput' + i).find('.ingredientAmountLabel').attr('for', 'ingredients[' + replaceIndex + '].amount');
        $('#clonedInput' + i).find('.ingredientAmountInput').attr('name', 'ingredients[' +replaceIndex + '].amount');
        $('#clonedInput' + i).find('.ingredientAmountInput').attr('name', 'ingredients[' + replaceIndex + '].amount');
        $('#clonedInput' + i).find('.delete-ingredient-button').attr('id', replaceIndex);
        $('#clonedInput' + i).attr('id', 'cloneInput' + replaceFor);
    }

    if (num > 1) {
        $(button).parent().remove();
    }
};

$(function () {
    $('#btnAdd_1').click(function () {
        var num = $('.cloneInput_1').length,
            newNum = new Number(num + 1),
            newElem = $('#clonedInput' + num).clone().attr('id', 'clonedInput' + newNum).fadeIn('slow');

        $('#clonedInput' + num).after(newElem);

        $('#btnDel_1').attr('disabled', false);
    });


    $('#btnDel_1').click(function () {

        var num = $('.clonedInput_1').length;
        $('#clonedInput' + num).slideUp('slow', function () {
            $(this).remove();
            if (num - 1 === 1)
                $('#btnDel_1').attr('disabled', true);
            $('#btnAdd_1').attr('disabled', false).prop('value', "Add Ingredient");
            $('#btnAdd_1').attr('disabled', false).text("Add Ingredient");
        });

        return false;
    });

    $('#btnAdd_2').click(function () {
        var num = $('.cloneInput_1').length,
            newNum = new Number(num + 1),
            newElem = $('#cloneInput' + num).clone().attr('id', 'cloneInput' + newNum).fadeIn('slow');

        newElem.find('.ingredientLabel').attr('for', 'ingredients[' + num + '].ingredient.id');
        newElem.find('.ingredientSelect').attr('name', 'ingredients[' + num + '].ingredient.id');
        newElem.find('.ingredientAmountLabel').attr('for', 'ingredients[' + num + '].amount');
        newElem.find('.ingredientAmountInput').attr('name', 'ingredients[' + num + '].amount');
        newElem.find('.delete-ingredient-button').attr('id', num);

        $('#cloneInput' + num).after(newElem);
    });

    $('#btnDel_2').click(function () {

        var num = $('.cloneInput_1').length;
        $('#cloneInput' + num).slideUp('slow', function () {
            $(this).remove();
            if (num - 1 === 1)
                $('#btnDel_2').attr('disabled', true);
            $('#btnAdd_2').attr('disabled', false).prop('value', "Add Ingredient");
            $('#btnAdd_2').attr('disabled', false).text("Add Ingredient");
        });

        return false;
    });

    $(window).on('load', function () {
        var ingredients_count = 1;
        localStorage.setItem("count_add_ingredients", JSON.stringify(ingredients_count));
        localStorage.setItem("count_new_recipe", JSON.stringify(ingredients_count));
        localStorage.setItem("count_edit_recipe", JSON.stringify(ingredients_count));
        var win = $(this); //this = window
        if (win.width() > 790) {
            $("#filters-big-card").append($("#filters-card").show());
            $("#user-big-card").append($("#user-card").show());
        }
        if (win.width() <= 790) {
            $("#filters-modal-body").append($("#filters-card").show());
            $("#user-modal-body").append($("#user-card").show());
        }
    });

    $(window).on('resize', function () {
        var win = $(this); //this = window
        if (win.width() > 790) {
            $("#filters-big-card").append($("#filters-card").show());
            $("#user-big-card").append($("#user-card").show());
        }
        if (win.width() <= 790) {
            $("#filters-modal-body").append($("#filters-card").show());
            $("#user-modal-body").append($("#user-card").show());
        }
    });


});