<%--
  Created by IntelliJ IDEA.
  User: agusosimani
  Date: 29/04/19
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <body>
    <!-- Modal -->
    <div class="modal fade" id="add-new-recipe" tabindex="-1" role="form" >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h3>Add New Recipe</h3>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form autocomplete="off">

              <div class="input-group mb-4 w-50">
                <div class="custom-file">
                  <input type="file" class="custom-file-input" id="fileInput" aria-describedby="fileInput">
                  <label class="custom-file-label" for="fileInput">Image</label>
                </div>
              </div>

              <label for="recipe_title">Recipe Title
                <a href="#" class="tooltip-test" title="Be descriptive — but don't get crazy. Succinct titles with well-chosen adjectives and key ingredients are memorable and they catch our attention, like 'One-Pot Kale and Quinoa Pilaf', 'Aunt Mariah's Lemon Sponge Cups' or 'Tipsy Maple Corn'. (You want to go cook all three, don't you?)">
                  <i class="fas fa-info-circle"></i>
                </a>
              </label>
              <input type="text" id="recipe_title" class="form-control mb-4" placeholder="What's the title of your recipe?">

              <label for="recipe_description">Recipe Description
                <a href="#" class="tooltip-test" title="This will be your recipe's introduction! We love a good story behind a dish, along with helpful tips and variations.
    If you've adapted from someone else's recipe, this is where you should give credit and tell us how you've made it your own. Not sure if your recipe is adapted enough?">
                  <i class="fas fa-info-circle"></i>
                </a>
              </label>
              <input type="text" id="recipe_description" class="form-control mb-4" placeholder="Tell us about your recipe.">

              <label for="textarea">Instructions</label>
              <textarea id="textarea" class="form-control mb-4" placeholder="How is it done?"></textarea>

              <div class="line mb-4">
                <label class="label-name left">This recipe</label>
                <select class="browser-default custom-select left mb-4" id="select">
                  <option value="Serves" selected="">Serves</option>
                  <option value="Makes">Makes</option>
                </select>
                <input id="serving_size_field" class="input-form left mb-4" type="text" name="recipe[serving_size]">
              </div>

              <div class="line mb-4">
                <label class="label-name left" for="prep_time">Prep Time
                  <a href="#" class="tooltip-test label-name" title="What is prep time?
    How long it takes to get your ingredients ready, both active (scrubbing potatoes or chopping carrots) and inactive (dough rising or meat resting). We all slice and dice differently, so don't stress too much about counting minutes.">
                    <i class="fas fa-info-circle"></i>
                  </a>
                </label>
                <input class="input-form w-10" type="number" min="0" data-name="prep_time" data-interval="hrs" value="">
                <span>hrs</span>
                <input class="input-form w-10" type="number" min="0" max="55" data-name="prep_time" data-interval="min" step="5" value="">
                <span>min</span>
                <input id="prep_time" value="" type="hidden" name="recipe[prep_time]">
              </div>

              <div class="line mb-4">
                <label class="label-name left" for="cook_time">Cook Time
                  <a href="#" class="tooltip-test label-name" title="What is cook time?
    How long it takes every part of the recipe to cook or bake, both active (stir-frying greens or scrambling eggs) and inactive (braising chicken or baking cake—mmm, cake).">
                    <i class="fas fa-info-circle"></i>
                  </a>
                </label>
                <input class="input-form w-10" type="number" min="0" data-name="cook_time" data-interval="hrs" value="">
                <span>hrs</span>
                <input class="input-form w-10" type="number" min="0" data-name="cook_time" data-interval="min" step="5" value="">
                <span>min</span>
                <input id="cook_time" value="" type="hidden" name="recipe[cook_time]">
              </div>

              <label>List your ingredients one at a time</label>
              <div class="line mb-4">
                <label class="label-name left" for="Item">Item</label>
                <div class="autocomplete">
                  <input class="input-form" id="Item" type="text">
                </div>
                <span>Quantity</span>
                <input class="input-form w-10" id="Qty" type="number">
                <span>tbs</span>
              </div>
              <%--TODO agregar javascript para replicar el elemento de arriba--%>
              <%--<a id="add_ingredient" href="javascript:void(0);" class="recipe-form__add js-add-ingredient" data-recipe-obj="recipe_components[component_0]">Add Another Ingredient</a>--%>
              <a class="line mb-4">
                <i class="fas fa-plus-circle"></i>
                Add Another Ingredient
              </a>

              <%--TODO poner lineas divisoras entre secciones de la receta--%>
              <label>Tag your recipe</label>

            </form>
          </div>
          <div class="modal-footer">
            <a class="btn btn-blue-grey" data-dismiss="modal">Close</a>
            <button type="button" class="btn btn-green">Save changes</button>
          </div>
        </div>
      </div>
    </div>

    <%--<fieldset class="fieldset fieldset--border">--%>
    <%--<label class="recipe-form__label">--%>
    <%--Tag your recipe: *--%>
    <%--<span class="recipe-form__tooltip-icon">--%>
    <%--<div class="tooltip tooltip--small recipe-form__tooltip">--%>
    <%--<div class="tooltip__arrow"></div>--%>
    <%--The more tags you select, the more your recipe will be seen across Food52. So go on—get all Type A and categorize your heart out.--%>
    <%--</div>--%>
    <%--</span>--%>
    <%--</label>--%>

    <%--<input type="hidden" name="tags" id="tags_hidden" value=",545">--%>

    <%--<ul class="recipe-form__tags more_tags">--%>
    <%--<li class="recipe-form__tag " id="meal_type_tag_field">--%>
    <%--<a data-name="list_0" class="recipe-form__tag-heading js-toggle-menu" id="heading_add_6">Meal<span class="icon__arrow icon__arrow--right"></span></a>--%>
    <%--<div id="tag_heading_6">--%>
    <%--<ul class="tags" id="tag">--%>
    <%--<li class="recipe-form__tags-item" id="li_545"><span>Dessert</span><a href="javascript:void(0);" tag_id="545" heading_id="6" class="recipe-form__remove remove js-remove-tag" id="tag"></a></li></ul>--%>
    <%--</div>--%>

    <%--<a style="" href="javascript:void(0);" data-name="list_0" class="recipe-form__add recipe-form__add--margin js-toggle-menu" id="heading_sub_add_6">Add Another Meal</a>--%>

    <%--<ul class="tag_list recipe-form__tag-list" id="list_0" style="display: none;">--%>
    <%--<a href="javascript:void(0);" data-name="list_0" class="recipe-form__close js-toggle-menu"></a>--%>
    <%--<li id="541">--%>
    <%--<a id="541" name="Appetizer" heading="6" class="add_tag">--%>
    <%--Appetizer--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="542">--%>
    <%--<a id="542" name="Breakfast" heading="6" class="add_tag">--%>
    <%--Breakfast--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="545">--%>
    <%--<a id="545" name="Dessert" heading="6" class="add_tag" style="font-weight: bold;">--%>
    <%--Dessert--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="553">--%>
    <%--<a id="553" name="Entree" heading="6" class="add_tag">--%>
    <%--Entree--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="547">--%>
    <%--<a id="547" name="Hors D'Oeuvre" heading="6" class="add_tag">--%>
    <%--Hors D'Oeuvre--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="550">--%>
    <%--<a id="550" name="Side" heading="6" class="add_tag">--%>
    <%--Side--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="552">--%>
    <%--<a id="552" name="Snack" heading="6" class="add_tag">--%>
    <%--Snack--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</li>--%>
    <%--<li class="recipe-form__tag ">--%>
    <%--<a data-name="list_1" class="recipe-form__tag-heading js-toggle-menu" id="heading_add_3">Dish Type<span class="icon__arrow icon__arrow--right"></span></a>--%>
    <%--<div id="tag_heading_3">--%>
    <%--<ul class="tags" id="tag">--%>
    <%--</ul>--%>
    <%--</div>--%>

    <%--<a style="display:none;" href="javascript:void(0);" data-name="list_1" class="recipe-form__add recipe-form__add--margin js-toggle-menu" id="heading_sub_add_3">Add Another Dish Type</a>--%>

    <%--<ul class="tag_list recipe-form__tag-list" id="list_1" style="display: none;">--%>
    <%--<a href="javascript:void(0);" data-name="list_1" class="recipe-form__close js-toggle-menu"></a>--%>
    <%--<li id="99">--%>
    <%--<a id="99" name="Bread" heading="3" class="add_tag">--%>
    <%--Bread--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="102">--%>
    <%--<a id="102" name="Cake" heading="3" class="add_tag">--%>
    <%--Cake--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="103">--%>
    <%--<a id="103" name="Candy" heading="3" class="add_tag">--%>
    <%--Candy--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="828">--%>
    <%--<a id="828" name="Carrot Cake" heading="3" class="add_tag">--%>
    <%--Carrot Cake--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="830">--%>
    <%--<a id="830" name="Chicken Salad" heading="3" class="add_tag">--%>
    <%--Chicken Salad--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="832">--%>
    <%--<a id="832" name="Chocolate Cake" heading="3" class="add_tag">--%>
    <%--Chocolate Cake--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="833">--%>
    <%--<a id="833" name="Chocolate Chip Cookies" heading="3" class="add_tag">--%>
    <%--Chocolate Chip Cookies--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="109">--%>
    <%--<a id="109" name="Cocktail" heading="3" class="add_tag">--%>
    <%--Cocktail--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="111">--%>
    <%--<a id="111" name="Cookie" heading="3" class="add_tag">--%>
    <%--Cookie--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="836">--%>
    <%--<a id="836" name="Cornbread" heading="3" class="add_tag">--%>
    <%--Cornbread--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="834">--%>
    <%--<a id="834" name="Corn Chowder" heading="3" class="add_tag">--%>
    <%--Corn Chowder--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="835">--%>
    <%--<a id="835" name="Corn Salad" heading="3" class="add_tag">--%>
    <%--Corn Salad--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="837">--%>
    <%--<a id="837" name="Fish Taco" heading="3" class="add_tag">--%>
    <%--Fish Taco--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="165">--%>
    <%--<a id="165" name="Ice Cream/Frozen Desserts" heading="3" class="add_tag">--%>
    <%--Ice Cream/Frozen Desserts--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="166">--%>
    <%--<a id="166" name="Pasta" heading="3" class="add_tag">--%>
    <%--Pasta--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="141">--%>
    <%--<a id="141" name="Pie" heading="3" class="add_tag">--%>
    <%--Pie--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="142">--%>
    <%--<a id="142" name="Pizza" heading="3" class="add_tag">--%>
    <%--Pizza--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="149">--%>
    <%--<a id="149" name="Salad" heading="3" class="add_tag">--%>
    <%--Salad--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="152">--%>
    <%--<a id="152" name="Sandwich" heading="3" class="add_tag">--%>
    <%--Sandwich--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="169">--%>
    <%--<a id="169" name="Soup" heading="3" class="add_tag">--%>
    <%--Soup--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="164">--%>
    <%--<a id="164" name="Stew" heading="3" class="add_tag">--%>
    <%--Stew--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="842">--%>
    <%--<a id="842" name="Zucchini Bread" heading="3" class="add_tag">--%>
    <%--Zucchini Bread--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</li>--%>
    <%--<li class="recipe-form__tag ">--%>
    <%--<a data-name="list_2" class="recipe-form__tag-heading js-toggle-menu" id="heading_add_5">Ingredient<span class="icon__arrow icon__arrow--right"></span></a>--%>
    <%--<div id="tag_heading_5">--%>
    <%--<ul class="tags" id="tag">--%>
    <%--</ul>--%>
    <%--</div>--%>

    <%--<a style="display:none;" href="javascript:void(0);" data-name="list_3" class="recipe-form__add recipe-form__add--margin js-toggle-menu" id="heading_sub_add_9">Add Another Special Consideration</a>--%>

    <%--<ul class="tag_list recipe-form__tag-list" id="list_3" style="display: none;">--%>
    <%--<a href="javascript:void(0);" data-name="list_3" class="recipe-form__close js-toggle-menu"></a>--%>
    <%--<li id="656">--%>
    <%--<a id="656" name="Alcohol-Free Drinks" heading="9" class="add_tag">--%>
    <%--Alcohol-Free Drinks--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="655">--%>
    <%--<a id="655" name="Gluten-Free" heading="9" class="add_tag">--%>
    <%--Gluten-Free--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="653">--%>
    <%--<a id="653" name="Vegan" heading="9" class="add_tag">--%>
    <%--Vegan--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="652">--%>
    <%--<a id="652" name="Vegetarian" heading="9" class="add_tag">--%>
    <%--Vegetarian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</li>--%>
    <%--<li class="recipe-form__tag ">--%>
    <%--<a data-name="list_4" class="recipe-form__tag-heading js-toggle-menu" id="heading_add_7">Occasion<span class="icon__arrow icon__arrow--right"></span></a>--%>
    <%--<div id="tag_heading_7">--%>
    <%--<ul class="tags" id="tag">--%>
    <%--</ul>--%>
    <%--</div>--%>

    <%--<a style="display:none;" href="javascript:void(0);" data-name="list_4" class="recipe-form__add recipe-form__add--margin js-toggle-menu" id="heading_sub_add_7">Add Another Occasion</a>--%>

    <%--<ul class="tag_list recipe-form__tag-list" id="list_4" style="display: none;">--%>
    <%--<a href="javascript:void(0);" data-name="list_4" class="recipe-form__close js-toggle-menu"></a>--%>
    <%--<li id="559">--%>
    <%--<a id="559" name="Christmas" heading="7" class="add_tag">--%>
    <%--Christmas--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="564">--%>
    <%--<a id="564" name="Easter" heading="7" class="add_tag">--%>
    <%--Easter--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="567">--%>
    <%--<a id="567" name="Fall" heading="7" class="add_tag">--%>
    <%--Fall--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="569">--%>
    <%--<a id="569" name="Father's Day" heading="7" class="add_tag">--%>
    <%--Father's Day--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="570">--%>
    <%--<a id="570" name="Fourth of July" heading="7" class="add_tag">--%>
    <%--Fourth of July--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="573">--%>
    <%--<a id="573" name="Halloween" heading="7" class="add_tag">--%>
    <%--Halloween--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="574">--%>
    <%--<a id="574" name="Hanukkah" heading="7" class="add_tag">--%>
    <%--Hanukkah--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="580">--%>
    <%--<a id="580" name="Memorial Day" heading="7" class="add_tag">--%>
    <%--Memorial Day--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="581">--%>
    <%--<a id="581" name="Mother's Day" heading="7" class="add_tag">--%>
    <%--Mother's Day--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="587">--%>
    <%--<a id="587" name="Passover" heading="7" class="add_tag">--%>
    <%--Passover--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="594">--%>
    <%--<a id="594" name="Rosh Hashanah" heading="7" class="add_tag">--%>
    <%--Rosh Hashanah--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="597">--%>
    <%--<a id="597" name="Spring" heading="7" class="add_tag">--%>
    <%--Spring--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="601">--%>
    <%--<a id="601" name="Summer" heading="7" class="add_tag">--%>
    <%--Summer--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="604">--%>
    <%--<a id="604" name="Thanksgiving" heading="7" class="add_tag">--%>
    <%--Thanksgiving--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="605">--%>
    <%--<a id="605" name="Valentine's Day" heading="7" class="add_tag">--%>
    <%--Valentine's Day--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="612">--%>
    <%--<a id="612" name="Winter" heading="7" class="add_tag">--%>
    <%--Winter--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</li>--%>
    <%--<li class="recipe-form__tag ">--%>
    <%--<a data-name="list_5" class="recipe-form__tag-heading js-toggle-menu" id="heading_add_8">Preparation<span class="icon__arrow icon__arrow--right"></span></a>--%>
    <%--<div id="tag_heading_8">--%>
    <%--<ul class="tags" id="tag">--%>
    <%--</ul>--%>
    <%--</div>--%>

    <%--<a style="display:none;" href="javascript:void(0);" data-name="list_5" class="recipe-form__add recipe-form__add--margin js-toggle-menu" id="heading_sub_add_8">Add Another Preparation</a>--%>

    <%--<ul class="tag_list recipe-form__tag-list" id="list_5" style="display: none;">--%>
    <%--<a href="javascript:void(0);" data-name="list_5" class="recipe-form__close js-toggle-menu"></a>--%>
    <%--<li id="646">--%>
    <%--<a id="646" name="5 Ingredients or Fewer" heading="8" class="add_tag">--%>
    <%--5 Ingredients or Fewer--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="618">--%>
    <%--<a id="618" name="Grill/Barbecue" heading="8" class="add_tag">--%>
    <%--Grill/Barbecue--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="644">--%>
    <%--<a id="644" name="Make Ahead" heading="8" class="add_tag">--%>
    <%--Make Ahead--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="642">--%>
    <%--<a id="642" name="One-Pot Wonders" heading="8" class="add_tag">--%>
    <%--One-Pot Wonders--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="651">--%>
    <%--<a id="651" name="Serves a Crowd" heading="8" class="add_tag">--%>
    <%--Serves a Crowd--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="647">--%>
    <%--<a id="647" name="Slow Cook" heading="8" class="add_tag">--%>
    <%--Slow Cook--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</li>--%>
    <%--<li class="recipe-form__tag  recipe-form__tag--first" id="meal_type_cuisine_tag_field">--%>
    <%--<a data-name="list_6" class="recipe-form__tag-heading js-toggle-menu" id="heading_add_2">Cuisine<span class="icon__arrow icon__arrow--right"></span></a>--%>
    <%--<div id="tag_heading_2">--%>
    <%--<ul class="tags" id="tag">--%>
    <%--</ul>--%>
    <%--</div>--%>

    <%--<a style="display:none;" href="javascript:void(0);" data-name="list_6" class="recipe-form__add recipe-form__add--margin js-toggle-menu" id="heading_sub_add_2">Add Another Cuisine</a>--%>

    <%--<ul class="tag_list recipe-form__tag-list" id="list_6" style="display: none;">--%>
    <%--<a href="javascript:void(0);" data-name="list_6" class="recipe-form__close js-toggle-menu"></a>--%>
    <%--<li id="6">--%>
    <%--<a id="6" name="African" heading="2" class="add_tag">--%>
    <%--African--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="7">--%>
    <%--<a id="7" name="American" heading="2" class="add_tag">--%>
    <%--American--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="8">--%>
    <%--<a id="8" name="Argentine" heading="2" class="add_tag">--%>
    <%--Argentine--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="9">--%>
    <%--<a id="9" name="Ashkenazi" heading="2" class="add_tag">--%>
    <%--Ashkenazi--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="10">--%>
    <%--<a id="10" name="Asian" heading="2" class="add_tag">--%>
    <%--Asian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="11">--%>
    <%--<a id="11" name="Australian/New Zealander" heading="2" class="add_tag">--%>
    <%--Australian/New Zealander--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="12">--%>
    <%--<a id="12" name="Austrian" heading="2" class="add_tag">--%>
    <%--Austrian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="13">--%>
    <%--<a id="13" name="Bangladeshi" heading="2" class="add_tag">--%>
    <%--Bangladeshi--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="14">--%>
    <%--<a id="14" name="Basque" heading="2" class="add_tag">--%>
    <%--Basque--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="15">--%>
    <%--<a id="15" name="Belgian" heading="2" class="add_tag">--%>
    <%--Belgian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="16">--%>
    <%--<a id="16" name="Brazilian" heading="2" class="add_tag">--%>
    <%--Brazilian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="17">--%>
    <%--<a id="17" name="British" heading="2" class="add_tag">--%>
    <%--British--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="18">--%>
    <%--<a id="18" name="Cajun/Creole" heading="2" class="add_tag">--%>
    <%--Cajun/Creole--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="19">--%>
    <%--<a id="19" name="Californian" heading="2" class="add_tag">--%>
    <%--Californian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="20">--%>
    <%--<a id="20" name="Canadian" heading="2" class="add_tag">--%>
    <%--Canadian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="21">--%>
    <%--<a id="21" name="Cantonese" heading="2" class="add_tag">--%>
    <%--Cantonese--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="22">--%>
    <%--<a id="22" name="Caribbean" heading="2" class="add_tag">--%>
    <%--Caribbean--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="23">--%>
    <%--<a id="23" name="Central American" heading="2" class="add_tag">--%>
    <%--Central American--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="24">--%>
    <%--<a id="24" name="Central Asian" heading="2" class="add_tag">--%>
    <%--Central Asian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="25">--%>
    <%--<a id="25" name="Chilean" heading="2" class="add_tag">--%>
    <%--Chilean--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="26">--%>
    <%--<a id="26" name="Chinese" heading="2" class="add_tag">--%>
    <%--Chinese--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="27">--%>
    <%--<a id="27" name="Colombian" heading="2" class="add_tag">--%>
    <%--Colombian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="28">--%>
    <%--<a id="28" name="Cuban" heading="2" class="add_tag">--%>
    <%--Cuban--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="29">--%>
    <%--<a id="29" name="Czech" heading="2" class="add_tag">--%>
    <%--Czech--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="30">--%>
    <%--<a id="30" name="Danish" heading="2" class="add_tag">--%>
    <%--Danish--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="5">--%>
    <%--<a id="5" name="Drinks" heading="2" class="add_tag">--%>
    <%--Drinks--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="31">--%>
    <%--<a id="31" name="Dutch" heading="2" class="add_tag">--%>
    <%--Dutch--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="32">--%>
    <%--<a id="32" name="East African" heading="2" class="add_tag">--%>
    <%--East African--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="33">--%>
    <%--<a id="33" name="Eastern European" heading="2" class="add_tag">--%>
    <%--Eastern European--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="34">--%>
    <%--<a id="34" name="Egyptian" heading="2" class="add_tag">--%>
    <%--Egyptian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="35">--%>
    <%--<a id="35" name="Ethiopian" heading="2" class="add_tag">--%>
    <%--Ethiopian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="36">--%>
    <%--<a id="36" name="European" heading="2" class="add_tag">--%>
    <%--European--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="37">--%>
    <%--<a id="37" name="Filipino" heading="2" class="add_tag">--%>
    <%--Filipino--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="38">--%>
    <%--<a id="38" name="Finnish" heading="2" class="add_tag">--%>
    <%--Finnish--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="39">--%>
    <%--<a id="39" name="French" heading="2" class="add_tag">--%>
    <%--French--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="40">--%>
    <%--<a id="40" name="French Provençal" heading="2" class="add_tag">--%>
    <%--French Provençal--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="41">--%>
    <%--<a id="41" name="German" heading="2" class="add_tag">--%>
    <%--German--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="42">--%>
    <%--<a id="42" name="Greek" heading="2" class="add_tag">--%>
    <%--Greek--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="806">--%>
    <%--<a id="806" name="Hunan" heading="2" class="add_tag">--%>
    <%--Hunan--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="43">--%>
    <%--<a id="43" name="Hungarian" heading="2" class="add_tag">--%>
    <%--Hungarian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="44">--%>
    <%--<a id="44" name="Indian" heading="2" class="add_tag">--%>
    <%--Indian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="45">--%>
    <%--<a id="45" name="Indonesian" heading="2" class="add_tag">--%>
    <%--Indonesian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="46">--%>
    <%--<a id="46" name="Irish" heading="2" class="add_tag">--%>
    <%--Irish--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="47">--%>
    <%--<a id="47" name="Israeli" heading="2" class="add_tag">--%>
    <%--Israeli--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="48">--%>
    <%--<a id="48" name="Italian" heading="2" class="add_tag">--%>
    <%--Italian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="49">--%>
    <%--<a id="49" name="Jamaican" heading="2" class="add_tag">--%>
    <%--Jamaican--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="50">--%>
    <%--<a id="50" name="Japanese" heading="2" class="add_tag">--%>
    <%--Japanese--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="51">--%>
    <%--<a id="51" name="Jewish" heading="2" class="add_tag">--%>
    <%--Jewish--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="52">--%>
    <%--<a id="52" name="Korean" heading="2" class="add_tag">--%>
    <%--Korean--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="53">--%>
    <%--<a id="53" name="Latin-American" heading="2" class="add_tag">--%>
    <%--Latin-American--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="54">--%>
    <%--<a id="54" name="Lebanese" heading="2" class="add_tag">--%>
    <%--Lebanese--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="55">--%>
    <%--<a id="55" name="Malaysian" heading="2" class="add_tag">--%>
    <%--Malaysian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="56">--%>
    <%--<a id="56" name="Mediterranean" heading="2" class="add_tag">--%>
    <%--Mediterranean--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="57">--%>
    <%--<a id="57" name="Mexican" heading="2" class="add_tag">--%>
    <%--Mexican--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="58">--%>
    <%--<a id="58" name="Middle Eastern" heading="2" class="add_tag">--%>
    <%--Middle Eastern--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="59">--%>
    <%--<a id="59" name="Midwestern" heading="2" class="add_tag">--%>
    <%--Midwestern--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="60">--%>
    <%--<a id="60" name="Moroccan" heading="2" class="add_tag">--%>
    <%--Moroccan--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="61">--%>
    <%--<a id="61" name="New England" heading="2" class="add_tag">--%>
    <%--New England--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="62">--%>
    <%--<a id="62" name="North African" heading="2" class="add_tag">--%>
    <%--North African--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="63">--%>
    <%--<a id="63" name="Northern Italian" heading="2" class="add_tag">--%>
    <%--Northern Italian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="64">--%>
    <%--<a id="64" name="Norwegian" heading="2" class="add_tag">--%>
    <%--Norwegian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="65">--%>
    <%--<a id="65" name="Nuevo Latino" heading="2" class="add_tag">--%>
    <%--Nuevo Latino--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="66">--%>
    <%--<a id="66" name="Pacific Northwest" heading="2" class="add_tag">--%>
    <%--Pacific Northwest--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="67">--%>
    <%--<a id="67" name="Pakistani" heading="2" class="add_tag">--%>
    <%--Pakistani--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="68">--%>
    <%--<a id="68" name="Persian" heading="2" class="add_tag">--%>
    <%--Persian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="69">--%>
    <%--<a id="69" name="Peruvian" heading="2" class="add_tag">--%>
    <%--Peruvian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="70">--%>
    <%--<a id="70" name="Polish" heading="2" class="add_tag">--%>
    <%--Polish--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="85">--%>
    <%--<a id="85" name="Portuguese" heading="2" class="add_tag">--%>
    <%--Portuguese--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="71">--%>
    <%--<a id="71" name="Puerto Rican" heading="2" class="add_tag">--%>
    <%--Puerto Rican--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="72">--%>
    <%--<a id="72" name="Quebec" heading="2" class="add_tag">--%>
    <%--Quebec--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="73">--%>
    <%--<a id="73" name="Russian" heading="2" class="add_tag">--%>
    <%--Russian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="74">--%>
    <%--<a id="74" name="Scandinavian" heading="2" class="add_tag">--%>
    <%--Scandinavian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="75">--%>
    <%--<a id="75" name="Scottish" heading="2" class="add_tag">--%>
    <%--Scottish--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="76">--%>
    <%--<a id="76" name="Sephardic" heading="2" class="add_tag">--%>
    <%--Sephardic--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="78">--%>
    <%--<a id="78" name="South African" heading="2" class="add_tag">--%>
    <%--South African--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="79">--%>
    <%--<a id="79" name="South American" heading="2" class="add_tag">--%>
    <%--South American--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="77">--%>
    <%--<a id="77" name="South Asian" heading="2" class="add_tag">--%>
    <%--South Asian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="80">--%>
    <%--<a id="80" name="Southeast Asian" heading="2" class="add_tag">--%>
    <%--Southeast Asian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="81">--%>
    <%--<a id="81" name="Southern" heading="2" class="add_tag">--%>
    <%--Southern--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="82">--%>
    <%--<a id="82" name="Southern Italian" heading="2" class="add_tag">--%>
    <%--Southern Italian--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="83">--%>
    <%--<a id="83" name="Southwestern" heading="2" class="add_tag">--%>
    <%--Southwestern--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="84">--%>
    <%--<a id="84" name="Spanish" heading="2" class="add_tag">--%>
    <%--Spanish--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="86">--%>
    <%--<a id="86" name="Swedish" heading="2" class="add_tag">--%>
    <%--Swedish--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="87">--%>
    <%--<a id="87" name="Swiss" heading="2" class="add_tag">--%>
    <%--Swiss--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="88">--%>
    <%--<a id="88" name="Szechuan" heading="2" class="add_tag">--%>
    <%--Szechuan--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="89">--%>
    <%--<a id="89" name="Tex-Mex" heading="2" class="add_tag">--%>
    <%--Tex-Mex--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="90">--%>
    <%--<a id="90" name="Thai" heading="2" class="add_tag">--%>
    <%--Thai--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="92">--%>
    <%--<a id="92" name="Turkish" heading="2" class="add_tag">--%>
    <%--Turkish--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="93">--%>
    <%--<a id="93" name="Tuscan" heading="2" class="add_tag">--%>
    <%--Tuscan--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="95">--%>
    <%--<a id="95" name="Vietnamese" heading="2" class="add_tag">--%>
    <%--Vietnamese--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li id="96">--%>
    <%--<a id="96" name="Welsh" heading="2" class="add_tag">--%>
    <%--Welsh--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</fieldset>--%>

    <%--</div>--%>
    <%--</form>--%>
    <%--</div>--%>
    <%--<div class="modal-footer">--%>
    <%--<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>--%>
    <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
  </body>
</html>
