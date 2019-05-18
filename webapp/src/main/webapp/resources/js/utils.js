function add_rate(clicked_id){
    let searchParams = new URLSearchParams(window.location.search);
    //$.post("/rate_recipe", {rate: clicked_id, recipeId: searchParams.get("recipeId")});
    $.ajax({
        url: "/rate_recipe",
        data: {rate: clicked_id, recipeId: searchParams.get("recipeId")},
        type: "POST",
        complete:function() {
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

        $('#btnDel_1').attr('disabled', false);

        //Cambiar por el limite que se quiera
        if (newNum == 5)
            $('#btnAdd_1').attr('disabled', true).prop('value', "You've reached the limit");
        if (newNum == 5)
            $('#btnAdd_1').attr('disabled', true).text("You've reached the limit");
    });


    $('#btnDel_1').click(function () {
        if (confirm("Are you sure you wish to remove this email? This cannot be undone.")) {
            var num = $('.clonedInput_1').length;
            $('#clonedInput' + num).slideUp('slow', function () {
                $(this).remove();
                if (num - 1 === 1)
                    $('#btnDel_1').attr('disabled', true);
               $('#btnAdd_1').attr('disabled', false).prop('value', "Add Ingredient");
                $('#btnAdd_1').attr('disabled', false).text("Add Ingredient");
            });
        }
        return false;
    });
    $('#btnAdd_1').attr('disabled', false);
    $('#btnDel_1').attr('disabled', true);
});
