$(function () {

    for(var i = 1; i < formList.length; i++){
        var elem = i - 1;
        var num = $('.cloneInput_1').length,
            newNum = new Number(num + 1),
            newElem = $('#clonedInput' + num).clone().attr('id', 'clonedInput' + newNum).fadeIn('slow');

        $('#clonedInput' + num).after(newElem);

        $('#clonedInput' + num).find('.select-ingredient-recipe').val(formList[elem].id);

        $('#btnDel_1').attr('disabled', false);
    }
});