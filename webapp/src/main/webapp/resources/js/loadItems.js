$(function () {


    console.log("ENTRE");
    console.log(formList[0].amount);
    console.log(formList[0].ingredient.name);

    for(var i = 1; i <= formList.length; i++){
        var elem = i - 1;
        if(i == 1){
            $('#clonedInput1').find('.select-ingredient-recipe').val(formList[elem].ingredient.id);
            $('#clonedInput1').find('.select-ingredient-recipe-amount').val(formList[elem].amount);
        }
        else{
            console.log(i);

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