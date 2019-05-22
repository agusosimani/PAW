$(function () {

    for(var i = 1; i <= formList.length; i++){
        var elem = i - 1;
        if(i == 1){
            $('#clonedInput1').find('.select-ingredient-recipe').val(formList[elem].ingredient.id);
            $('#clonedInput1').find('.select-ingredient-recipe-amount').val(formList[elem].amount);
        }
        else{
            localStorage.setItem("count_edit_recipe", JSON.stringify(JSON.parse(localStorage.getItem("count_edit_recipe")) +1));
            var elem = i - 1;
            var num = $('.clonedInput_1').length,
                newNum = new Number(num + 1),
                newElem = $('#clonedInput' + num).clone().attr('id', 'clonedInput' + newNum).fadeIn('slow');

            $('#clonedInput' + num).after(newElem);

            $('#clonedInput' + num).find('.select-ingredient-recipe').val(formList[elem].ingredient.id);
            $('#clonedInput' + num).find('.select-ingredient-recipe-amount').val(formList[elem].amount);

            $('#btnDel_1').attr('disabled', false);
        }
    }
});