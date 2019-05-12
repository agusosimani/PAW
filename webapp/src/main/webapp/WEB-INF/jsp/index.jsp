<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Foodify</title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/foodify_tab.png"/>"/>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="<c:url value="/resources/css/mdb.css"/>" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/style_spotify.css"/>" rel="stylesheet">
  </head>

  <body>
    <%@include file="sidebar.jsp" %>

    <section class="main_container">
      <section class="browse text-center">
        <div class="row">
          <c:forEach var="recipe" items="${RecipeList}">
            <div class="col-lg-4 col-md-12 mb-lg-0 mb-4">

              <div class="card">
                <div class="view overlay rounded z-depth-2 mb-4">
                  <img class="card-img-top" src="<c:url value="/resources/img/recipe_1.png"/>" alt="Sample image">
                  <a>
                    <div class="mask rgba-white-slight"></div>
                  </a>
                </div>

                <div class="card-body">
                  <h4 class="card-title"><strong>${recipe.name}</strong></h4>
                  <p class="card-text">${recipe.description}</p>
                  <a class="btn btn-green btn-rounded btn-md">Read more</a>
                </div>

              </div>
            </div>
          </c:forEach>
        </div>


      </section>

      <section class="side_card">
        <div class="card">
          <div class="card-body">

            <h4>Filtros de busqueda</h4>
            <br/>

            <div class="navigation__list">

              <div class="navigation__list__header"
                   role="button"
                   data-toggle="collapse"
                   href="#sortBy"
                   aria-expanded="true"
                   aria-controls="yourMusic">
                Ordenar por
              </div>

              <div class="collapse in" id="sortBy">

                <div class="custom-control custom-radio">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios">
                  <label class="custom-control-label" for="defaultGroupExample1">Rising</label>
                </div>

                <div class="custom-control custom-radio">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" checked>
                  <label class="custom-control-label" for="defaultGroupExample2">New</label>
                </div>

                <div class="custom-control custom-radio">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios">
                  <label class="custom-control-label" for="defaultGroupExample3">Top</label>
                </div>

                <div class="custom-control custom-radio">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios">
                  <label class="custom-control-label" for="defaultGroupExample4">Hot</label>
                </div>

              </div>
            </div>


            <div class="navigation__list">

              <div class="navigation__list__header"
                   role="button"
                   data-toggle="collapse"
                   href="#foodType"
                   aria-expanded="true"
                   aria-controls="yourMusic">
                Tipo de cocina
              </div>

              <div class="collapse in" id="foodType">

                <div class="custom-control custom-checkbox">
                  <input type="checkbox" class="custom-control-input" id="defaultUnchecked">
                  <label class="custom-control-label" for="defaultUnchecked">Italiana</label>
                </div>
                <div class="custom-control custom-checkbox">
                  <input type="checkbox" class="custom-control-input" id="defaultUnchecked2">
                  <label class="custom-control-label" for="defaultUnchecked2">China</label>
                </div>
                <div class="custom-control custom-checkbox">
                  <input type="checkbox" class="custom-control-input" id="defaultUnchecked3">
                  <label class="custom-control-label" for="defaultUnchecked3">Española</label>
                </div>

                <div class="custom-control custom-checkbox">
                  <input type="checkbox" class="custom-control-input" id="defaultUnchecked4">
                  <label class="custom-control-label" for="defaultUnchecked4">Vegana</label>
                </div>

              </div>
            </div>

          </div>
        </div>
      </section>
    </section>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-green add" data-toggle="modal" data-target="#add-new-recipe">+</button>

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
            <p>Cupcake ipsum dolor sit amet icing unerdwear.com topping. I love muffin icing bonbon halvah. Jelly halvah macaroon I love pastry bonbon. Cookie carrot cake sweet roll. Gingerbread biscuit applicake soufflé liquorice candy canes tart jujubes muffin. Tiramisu I love biscuit dessert. Jelly tootsie roll candy canes marshmallow biscuit fruitcake. I love chocolate bar danish. I love marzipan pastry cupcake topping cookie wafer. Ice cream gingerbread topping donut soufflé. Jelly beans chocolate bar gummies pudding chocolate bar applicake cheesecake chocolate cake. Jelly beans ice cream sweet candy canes. Apple pie cake I love apple pie. Sweet roll I love toffee tart.</p>
            <p>Cupcake ipsum dolor sit amet icing unerdwear.com topping. I love muffin icing bonbon halvah. Jelly halvah macaroon I love pastry bonbon. Cookie carrot cake sweet roll. Gingerbread biscuit applicake soufflé liquorice candy canes tart jujubes muffin. Tiramisu I love biscuit dessert. Jelly tootsie roll candy canes marshmallow biscuit fruitcake. I love chocolate bar danish. I love marzipan pastry cupcake topping cookie wafer. Ice cream gingerbread topping donut soufflé. Jelly beans chocolate bar gummies pudding chocolate bar applicake cheesecake chocolate cake. Jelly beans ice cream sweet candy canes. Apple pie cake I love apple pie. Sweet roll I love toffee tart.</p>
            <p>Cupcake ipsum dolor sit amet icing unerdwear.com topping. I love muffin icing bonbon halvah. Jelly halvah macaroon I love pastry bonbon. Cookie carrot cake sweet roll. Gingerbread biscuit applicake soufflé liquorice candy canes tart jujubes muffin. Tiramisu I love biscuit dessert. Jelly tootsie roll candy canes marshmallow biscuit fruitcake. I love chocolate bar danish. I love marzipan pastry cupcake topping cookie wafer. Ice cream gingerbread topping donut soufflé. Jelly beans chocolate bar gummies pudding chocolate bar applicake cheesecake chocolate cake. Jelly beans ice cream sweet candy canes. Apple pie cake I love apple pie. Sweet roll I love toffee tart.</p>
            <p>Cupcake ipsum dolor sit amet icing unerdwear.com topping. I love muffin icing bonbon halvah. Jelly halvah macaroon I love pastry bonbon. Cookie carrot cake sweet roll. Gingerbread biscuit applicake soufflé liquorice candy canes tart jujubes muffin. Tiramisu I love biscuit dessert. Jelly tootsie roll candy canes marshmallow biscuit fruitcake. I love chocolate bar danish. I love marzipan pastry cupcake topping cookie wafer. Ice cream gingerbread topping donut soufflé. Jelly beans chocolate bar gummies pudding chocolate bar applicake cheesecake chocolate cake. Jelly beans ice cream sweet candy canes. Apple pie cake I love apple pie. Sweet roll I love toffee tart.</p>
            <p>Cupcake ipsum dolor sit amet icing unerdwear.com topping. I love muffin icing bonbon halvah. Jelly halvah macaroon I love pastry bonbon. Cookie carrot cake sweet roll. Gingerbread biscuit applicake soufflé liquorice candy canes tart jujubes muffin. Tiramisu I love biscuit dessert. Jelly tootsie roll candy canes marshmallow biscuit fruitcake. I love chocolate bar danish. I love marzipan pastry cupcake topping cookie wafer. Ice cream gingerbread topping donut soufflé. Jelly beans chocolate bar gummies pudding chocolate bar applicake cheesecake chocolate cake. Jelly beans ice cream sweet candy canes. Apple pie cake I love apple pie. Sweet roll I love toffee tart.</p>
            <p>Cupcake ipsum dolor sit amet icing unerdwear.com topping. I love muffin icing bonbon halvah. Jelly halvah macaroon I love pastry bonbon. Cookie carrot cake sweet roll. Gingerbread biscuit applicake soufflé liquorice candy canes tart jujubes muffin. Tiramisu I love biscuit dessert. Jelly tootsie roll candy canes marshmallow biscuit fruitcake. I love chocolate bar danish. I love marzipan pastry cupcake topping cookie wafer. Ice cream gingerbread topping donut soufflé. Jelly beans chocolate bar gummies pudding chocolate bar applicake cheesecake chocolate cake. Jelly beans ice cream sweet candy canes. Apple pie cake I love apple pie. Sweet roll I love toffee tart.</p>
          </div>
          <div class="modal-footer">
            <a class="btn btn-blue-grey" data-dismiss="modal">Close</a>
            <button type="button" class="btn btn-green">Save changes</button>
          </div>
        </div>
      </div>
    </div>
          <%--<div class="modal-body">--%>
            <%--<form class="recipe-form" id="recipe-submit-form" enctype="multipart/form-data" action="/recipes" accept-charset="UTF-8" method="post"><input name="utf8" type="hidden" value="✓"><input type="hidden" name="authenticity_token" value="9enO0ov8oaZbzOX/LUMVKyzhcd18eSRWMNlJLqc/DAkdCIwBuxe/aRqIQyDkaGpM2ncIJfxezt/a7iFeIvnzog==">--%>
              <%--<div class="form_inner recipe-form__inner">--%>
                <%--<fieldset class="fieldset">--%>
                  <%--<label class="recipe-form__label" for="title_field">--%>
                    <%--Recipe Title: *--%>
                    <%--<span class="recipe-form__tooltip-icon">--%>
          <%--<div class="tooltip tooltip--small recipe-form__tooltip">--%>
            <%--<div class="tooltip__arrow"></div>--%>
             <%--Be descriptive — but don't get crazy. Succinct titles with well-chosen adjectives and key ingredients are memorable and they catch our attention, like "One-Pot Kale and Quinoa Pilaf", "Aunt Mariah's Lemon Sponge Cups" or "Tipsy Maple Corn". (You want to go cook all three, don't you?)--%>
          <%--</div>--%>
        <%--</span>--%>
                  <%--</label>--%>

                  <%--<input id="title_field" maxlength="80" class="input--light" placeholder="What's the title of your recipe?" size="80" type="text" name="recipe[title]">--%>
                <%--</fieldset>--%>

                <%--<fieldset class="fieldset fieldset--border">--%>
                  <%--<label class="recipe-form__label" for="description_field">--%>
                    <%--Recipe Description:--%>
                    <%--<span class="recipe-form__tooltip-icon">--%>
          <%--<div class="tooltip tooltip--small recipe-form__tooltip">--%>
            <%--<div class="tooltip__arrow"></div>--%>
            <%--This will be your recipe's introduction! We love a good story behind a dish, along with helpful tips and variations. <br><br>If you've adapted from someone else's recipe, this is where you should give credit and tell us how you've made it your own. Not sure if your recipe is adapted enough?--%>
          <%--</div>--%>
        <%--</span>--%>
                  <%--</label>--%>

                  <%--<textarea id="description_field" class="textarea--light" placeholder="Tell us about your recipe." name="recipe[description]"></textarea>--%>
                <%--</fieldset>--%>

                <%--<fieldset class="fieldset">--%>
                  <%--<div class="recipe-form__row--narrow recipe-form__row--inline">--%>
        <%--<span class="recipe-form__label--serves">This recipe <select name="recipe[makes_serves]" id="recipe_makes_serves"><option selected="selected" value="Serves">Serves</option>--%>
<%--<option value="Makes">Makes</option></select>: *</span>--%>
                    <%--<span><input id="serving_size_field" class="input--light" type="text" name="recipe[serving_size]"></span>--%>
                  <%--</div>--%>
                <%--</fieldset>--%>

                <%--<fieldset class="fieldset">--%>
                  <%--<div class="recipe-form__row--narrow recipe-form__row--inline" id="prep_time_field">--%>
        <%--<span class="recipe-form__label--time">--%>
          <%--Prep Time: *--%>
          <%--<span class="recipe-form__tooltip-icon">--%>
            <%--<div class="tooltip tooltip--small recipe-form__tooltip">--%>
              <%--<div class="tooltip__arrow"></div>--%>
              <%--<strong>What is prep time?</strong><br>How long it takes to get your ingredients ready, both active (scrubbing potatoes or chopping carrots) and inactive (dough rising or meat resting). We all slice and dice differently, so don't stress too much about counting minutes.--%>
            <%--</div>--%>
          <%--</span>--%>
        <%--</span>--%>

                    <%--<div class="recipe-form__input-inset">--%>
                      <%--<input class="input--light js-time-input" type="number" min="0" data-name="prep_time" data-interval="hrs" value="">--%>
                      <%--<span>hrs</span>--%>
                    <%--</div>--%>
                    <%--<div class="recipe-form__input-inset">--%>
                      <%--<input class="input--light js-time-input" type="number" min="0" data-name="prep_time" data-interval="min" step="5" value="">--%>
                      <%--<span>min</span>--%>
                    <%--</div>--%>
                    <%--<input id="prep_time" value="" type="hidden" name="recipe[prep_time]">--%>
                  <%--</div>--%>
                <%--</fieldset>--%>

                <%--<fieldset class="fieldset fieldset--border">--%>
                  <%--<div class="recipe-form__row--narrow recipe-form__row--inline" id="cook_time_field">--%>
        <%--<span class="recipe-form__label--time">--%>
          <%--Cook Time: *--%>
          <%--<span class="recipe-form__tooltip-icon">--%>
            <%--<div class="tooltip tooltip--small recipe-form__tooltip">--%>
              <%--<div class="tooltip__arrow"></div>--%>
              <%--<strong>What is cook time?</strong><br>How long it takes every part of the recipe to cook or bake, both active (stir-frying greens or scrambling eggs) and inactive (braising chicken or baking cake—mmm, cake).--%>
            <%--</div>--%>
          <%--</span>--%>
        <%--</span>--%>

                    <%--<div class="recipe-form__input-inset">--%>
                      <%--<input class="input--light js-time-input" type="number" min="0" data-name="cook_time" data-interval="hrs" value="">--%>
                      <%--<span>hrs</span>--%>
                    <%--</div>--%>
                    <%--<div class="recipe-form__input-inset">--%>
                      <%--<input class="input--light js-time-input" type="number" min="0" data-name="cook_time" data-interval="min" step="5" value="">--%>
                      <%--<span>min</span>--%>
                    <%--</div>--%>
                    <%--<input id="cook_time" value="" type="hidden" name="recipe[cook_time]"><p></p>--%>
                  <%--</div>--%>
                <%--</fieldset>--%>

                <%--<div class="js-recipe-components">--%>

                  <%--<input value="AB_RECIPE_NO_TITLE" type="hidden" name="recipe_components[component_0][name]" id="recipe_components_component_0_name">--%>
                  <%--<input value="ab" type="hidden" name="recipe_components[component_0][recipe_style]" id="recipe_components_component_0_recipe_style">--%>
                  <%--<fieldset class="fieldset fieldset--border">--%>
                    <%--<label class="recipe-form__label">--%>
                      <%--List your ingredients one at a time: *--%>
                      <%--<span class="recipe-form__tooltip-icon">--%>
      <%--<div class="tooltip tooltip--small recipe-form__tooltip">--%>
        <%--<div class="tooltip__arrow"></div>--%>
         <%--We've chosen most common measurements to standardize recipes across the site, but don't worry if you don't see what you need. Say your recipe calls for 5 large bananas—no "cups", no "bunches". Just enter "5" under Quantity, skip the Measurement, and write in "large bananas" under Item. (It'll work, we promise.)--%>
      <%--</div>--%>
    <%--</span>--%>
                    <%--</label>--%>

                    <%--<ul id="sortable1" class="ingredients clearfix ui-sortable">--%>
                      <%--<li class="ingredient_single_form recipe-form__sortable-item ui-sortable-handle">--%>
                        <%--<span class="recipe-form__dragger dragger"></span>--%>


                        <%--<div class="recipe-form__ingredient-fields" id="recipe_ingredients_field">--%>
                          <%--<div class="ingredient">--%>
                            <%--<label class="recipe-form__label--small">Quantity</label>--%>
                            <%--<input id="quantity" class="input--light" value="" type="text" name="recipe_components[component_0][ingredients][0][quantity]">--%>
                          <%--</div>--%>
                          <%--<div class="ingredient">--%>
                            <%--<label class="recipe-form__label--small" id="measurement">Measurement</label>--%>
                            <%--<select name="recipe_components[component_0][ingredients][0][measurement_id]" id="recipe_components_component_0_ingredients_0_measurement_id"><option value="10"> (none)</option>--%>
                              <%--<option value="3">cup</option>--%>
                              <%--<option value="21">teaspoon</option>--%>
                              <%--<option value="20">tablespoon</option>--%>
                              <%--<option value="1">bunch</option>--%>
                              <%--<option value="2">cake</option>--%>
                              <%--<option value="4">dash</option>--%>
                              <%--<option value="5">drop</option>--%>
                              <%--<option value="6">gallon</option>--%>
                              <%--<option value="23">gram</option>--%>
                              <%--<option value="7">handful</option>--%>
                              <%--<option value="8">liter</option>--%>
                              <%--<option value="9">milliliter</option>--%>
                              <%--<option value="11">ounce</option>--%>
                              <%--<option value="12">packet</option>--%>
                              <%--<option value="13">piece</option>--%>
                              <%--<option value="14">pinch</option>--%>
                              <%--<option value="22">pint</option>--%>
                              <%--<option value="15">pound</option>--%>
                              <%--<option value="16">quart</option>--%>
                              <%--<option value="17">shot</option>--%>
                              <%--<option value="18">splash</option>--%>
                              <%--<option value="19">sprig</option></select>--%>
                          <%--</div>--%>
                          <%--<div class="ingredient" id="last">--%>
                            <%--<label class="recipe-form__label--small">Item</label>--%>
                            <%--<input id="item" class="input--light" value="" type="text" name="recipe_components[component_0][ingredients][0][item]">--%>
                          <%--</div>--%>
                        <%--</div>--%>
                        <%--<input value="0" id="position" type="hidden" name="recipe_components[component_0][ingredients][0][position]">--%>
                        <%--<a href="javascript:void(0);" class="remove recipe-form__remove js-remove-ingredient"></a>--%>
                      <%--</li>--%>

                      <%--<li class="ingredient_single_form recipe-form__sortable-item ui-sortable-handle">--%>
                        <%--<span class="recipe-form__dragger dragger"></span>--%>


                        <%--<div class="recipe-form__ingredient-fields">--%>
                          <%--<div class="ingredient">--%>
                            <%--<label class="recipe-form__label--small">Quantity</label>--%>
                            <%--<input id="quantity" class="input--light" value="" type="text" name="recipe_components[component_0][ingredients][1][quantity]">--%>
                          <%--</div>--%>
                          <%--<div class="ingredient">--%>
                            <%--<label class="recipe-form__label--small" id="measurement">Measurement</label>--%>
                            <%--<select name="recipe_components[component_0][ingredients][1][measurement_id]" id="recipe_components_component_0_ingredients_1_measurement_id"><option value="10"> (none)</option>--%>
                              <%--<option value="3">cup</option>--%>
                              <%--<option value="21">teaspoon</option>--%>
                              <%--<option value="20">tablespoon</option>--%>
                              <%--<option value="1">bunch</option>--%>
                              <%--<option value="2">cake</option>--%>
                              <%--<option value="4">dash</option>--%>
                              <%--<option value="5">drop</option>--%>
                              <%--<option value="6">gallon</option>--%>
                              <%--<option value="23">gram</option>--%>
                              <%--<option value="7">handful</option>--%>
                              <%--<option value="8">liter</option>--%>
                              <%--<option value="9">milliliter</option>--%>
                              <%--<option value="11">ounce</option>--%>
                              <%--<option value="12">packet</option>--%>
                              <%--<option value="13">piece</option>--%>
                              <%--<option value="14">pinch</option>--%>
                              <%--<option value="22">pint</option>--%>
                              <%--<option value="15">pound</option>--%>
                              <%--<option value="16">quart</option>--%>
                              <%--<option value="17">shot</option>--%>
                              <%--<option value="18">splash</option>--%>
                              <%--<option value="19">sprig</option></select>--%>
                          <%--</div>--%>
                          <%--<div class="ingredient" id="last">--%>
                            <%--<label class="recipe-form__label--small">Item</label>--%>
                            <%--<input id="item" class="input--light" value="" type="text" name="recipe_components[component_0][ingredients][1][item]">--%>
                          <%--</div>--%>
                        <%--</div>--%>
                        <%--<input value="1" id="position" type="hidden" name="recipe_components[component_0][ingredients][1][position]">--%>
                        <%--<a href="javascript:void(0);" class="remove recipe-form__remove js-remove-ingredient"></a>--%>
                      <%--</li>--%>

                      <%--<li class="ingredient_single_form recipe-form__sortable-item ui-sortable-handle">--%>
                        <%--<span class="recipe-form__dragger dragger"></span>--%>


                        <%--<div class="recipe-form__ingredient-fields">--%>
                          <%--<div class="ingredient">--%>
                            <%--<label class="recipe-form__label--small">Quantity</label>--%>
                            <%--<input id="quantity" class="input--light" value="" type="text" name="recipe_components[component_0][ingredients][2][quantity]">--%>
                          <%--</div>--%>
                          <%--<div class="ingredient">--%>
                            <%--<label class="recipe-form__label--small" id="measurement">Measurement</label>--%>
                            <%--<select name="recipe_components[component_0][ingredients][2][measurement_id]" id="recipe_components_component_0_ingredients_2_measurement_id"><option value="10"> (none)</option>--%>
                              <%--<option value="3">cup</option>--%>
                              <%--<option value="21">teaspoon</option>--%>
                              <%--<option value="20">tablespoon</option>--%>
                              <%--<option value="1">bunch</option>--%>
                              <%--<option value="2">cake</option>--%>
                              <%--<option value="4">dash</option>--%>
                              <%--<option value="5">drop</option>--%>
                              <%--<option value="6">gallon</option>--%>
                              <%--<option value="23">gram</option>--%>
                              <%--<option value="7">handful</option>--%>
                              <%--<option value="8">liter</option>--%>
                              <%--<option value="9">milliliter</option>--%>
                              <%--<option value="11">ounce</option>--%>
                              <%--<option value="12">packet</option>--%>
                              <%--<option value="13">piece</option>--%>
                              <%--<option value="14">pinch</option>--%>
                              <%--<option value="22">pint</option>--%>
                              <%--<option value="15">pound</option>--%>
                              <%--<option value="16">quart</option>--%>
                              <%--<option value="17">shot</option>--%>
                              <%--<option value="18">splash</option>--%>
                              <%--<option value="19">sprig</option></select>--%>
                          <%--</div>--%>
                          <%--<div class="ingredient" id="last">--%>
                            <%--<label class="recipe-form__label--small">Item</label>--%>
                            <%--<input id="item" class="input--light" value="" type="text" name="recipe_components[component_0][ingredients][2][item]">--%>
                          <%--</div>--%>
                        <%--</div>--%>
                        <%--<input value="2" id="position" type="hidden" name="recipe_components[component_0][ingredients][2][position]">--%>
                        <%--<a href="javascript:void(0);" class="remove recipe-form__remove js-remove-ingredient"></a>--%>
                      <%--</li>--%>

                    <%--</ul>--%>

                    <%--<div class="recipe-form__error at-least-one" style="display: none;">--%>
                      <%--You must have at least one ingredient in your recipe.--%>
                    <%--</div>--%>

                    <%--<a id="add_ingredient" href="javascript:void(0);" class="recipe-form__add js-add-ingredient" data-recipe-obj="recipe_components[component_0]">Add Another Ingredient</a>--%>
                  <%--</fieldset>--%>
                  <%--<fieldset class="fieldset fieldset--border">--%>
                    <%--<label class="recipe-form__label">--%>
                      <%--Add your instructions one at a time:--%>
                      <%--<span class="recipe-form__tooltip-icon">--%>
      <%--<div class="tooltip tooltip--small recipe-form__tooltip">--%>
        <%--<div class="tooltip__arrow"></div>--%>
         <%--Walk us through your recipe, in your voice. Visual and other descriptive clues are always helpful, along with details like time and temperature. For example: "Saute the onions in a large frying pan over medium heat until soft and caramelized, about 15 minutes."--%>
      <%--</div>--%>
    <%--</span>--%>
                    <%--</label>--%>

                    <%--<ul id="sortable2" class="ingredients clearfix ui-sortable">--%>

                      <%--<li class="instruction_single_form recipe-form__sortable-item ui-sortable-handle">--%>

                        <%--<span class="recipe-form__dragger dragger" id="tall"></span>--%>
                        <%--<div class="recipe-form__instruction-fields">--%>
                          <%--<textarea id="grow" class="textarea--light" name="recipe_components[component_0][instructions][0][body]"></textarea>--%>
                          <%--<input value="0" id="position" type="hidden" name="recipe_components[component_0][instructions][0][position]">--%>
                        <%--</div>--%>
                        <%--<a href="javascript:void(0);" class="recipe-form__remove remove js-remove-instruction"></a>--%>
                      <%--</li>--%>
                      <%--<li class="instruction_single_form recipe-form__sortable-item ui-sortable-handle">--%>

                        <%--<span class="recipe-form__dragger dragger" id="tall"></span>--%>
                        <%--<div class="recipe-form__instruction-fields">--%>
                          <%--<textarea id="grow" class="textarea--light" name="recipe_components[component_0][instructions][1][body]"></textarea>--%>
                          <%--<input value="1" id="position" type="hidden" name="recipe_components[component_0][instructions][1][position]">--%>
                        <%--</div>--%>
                        <%--<a href="javascript:void(0);" class="recipe-form__remove remove js-remove-instruction"></a>--%>
                      <%--</li>--%>

                    <%--</ul>--%>
                    <%--<a id="add_instruction" href="javascript:void(0);" class="recipe-form__add js-add-instruction" data-recipe-obj="recipe_components[component_0]"><span class="small_header" id="left">Add Another Step</span></a>--%>
                  <%--</fieldset>--%>


                <%--</div>--%>

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

                      <%--<a style="display:none;" href="javascript:void(0);" data-name="list_2" class="recipe-form__add recipe-form__add--margin js-toggle-menu" id="heading_sub_add_5">Add Another Ingredient</a>--%>

                      <%--<ul class="tag_list recipe-form__tag-list" id="list_2" style="display: none;">--%>
                        <%--<a href="javascript:void(0);" data-name="list_2" class="recipe-form__close js-toggle-menu"></a>--%>
                        <%--<li id="222">--%>
                          <%--<a id="222" name="Bean" heading="5" class="add_tag">--%>
                            <%--Bean--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="223">--%>
                          <%--<a id="223" name="Beef" heading="5" class="add_tag">--%>
                            <%--Beef--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="265">--%>
                          <%--<a id="265" name="Cheese" heading="5" class="add_tag">--%>
                            <%--Cheese--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="268">--%>
                          <%--<a id="268" name="Chicken" heading="5" class="add_tag">--%>
                            <%--Chicken--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="829">--%>
                          <%--<a id="829" name="Chicken Breast" heading="5" class="add_tag">--%>
                            <%--Chicken Breast--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="831">--%>
                          <%--<a id="831" name="Chicken Thigh" heading="5" class="add_tag">--%>
                            <%--Chicken Thigh--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="272">--%>
                          <%--<a id="272" name="Chocolate" heading="5" class="add_tag">--%>
                            <%--Chocolate--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="304">--%>
                          <%--<a id="304" name="Egg" heading="5" class="add_tag">--%>
                            <%--Egg--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="315">--%>
                          <%--<a id="315" name="Fruit" heading="5" class="add_tag">--%>
                            <%--Fruit--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="317">--%>
                          <%--<a id="317" name="Game" heading="5" class="add_tag">--%>
                            <%--Game--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="324">--%>
                          <%--<a id="324" name="Grains" heading="5" class="add_tag">--%>
                            <%--Grains--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="838">--%>
                          <%--<a id="838" name="Ground Beef" heading="5" class="add_tag">--%>
                            <%--Ground Beef--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="352">--%>
                          <%--<a id="352" name="Lamb" heading="5" class="add_tag">--%>
                            <%--Lamb--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="839">--%>
                          <%--<a id="839" name="Pickle" heading="5" class="add_tag">--%>
                            <%--Pickle--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="429">--%>
                          <%--<a id="429" name="Pork" heading="5" class="add_tag">--%>
                            <%--Pork--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="434">--%>
                          <%--<a id="434" name="Potato" heading="5" class="add_tag">--%>
                            <%--Potato--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="467">--%>
                          <%--<a id="467" name="Seafood" heading="5" class="add_tag">--%>
                            <%--Seafood--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="840">--%>
                          <%--<a id="840" name="Summer Squash" heading="5" class="add_tag">--%>
                            <%--Summer Squash--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="841">--%>
                          <%--<a id="841" name="Swiss Chard" heading="5" class="add_tag">--%>
                            <%--Swiss Chard--%>
                          <%--</a>--%>
                        <%--</li>--%>
                        <%--<li id="515">--%>
                          <%--<a id="515" name="Vegetable" heading="5" class="add_tag">--%>
                            <%--Vegetable--%>
                          <%--</a>--%>
                        <%--</li>--%>
                      <%--</ul>--%>
                    <%--</li>--%>
                    <%--<li class="recipe-form__tag ">--%>
                      <%--<a data-name="list_3" class="recipe-form__tag-heading js-toggle-menu" id="heading_add_9">Special Consideration<span class="icon__arrow icon__arrow--right"></span></a>--%>
                      <%--<div id="tag_heading_9">--%>
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

    <%--<div class="row">--%>
      <%--<div class="col-2">--%>
        <%--<button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-home"></i> Inicio</button>--%>
        <%--<button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-search"></i> Search</button>--%>
        <%--<button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-book-open"></i> Biblioteca</button>--%>
      <%--</div>--%>
      <%--<div class="col-10">--%>

      <%--</div>--%>
    <%--</div>--%>


    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="<c:url value="/resources/js/popper.min.js"/>"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="<c:url value="/resources/js/mdb.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/spotify.js"/>"></script>

  </body>

</html>
