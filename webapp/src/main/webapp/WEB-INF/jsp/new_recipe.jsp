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
              <a class="line mb-4" id="add_ingredient_item">
                <i class="fas fa-plus-circle"></i>
                Add Another Ingredient
              </a>

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

              <label>Tag your recipe</label>
              <input type="text" id="cuisine" class="form-control mb-4" placeholder="Cuisine">
              <input type="text" id="special_consideration" class="form-control mb-4" placeholder="Special Consideration">

            </form>
          </div>
          <div class="modal-footer">
            <a class="btn btn-blue-grey" data-dismiss="modal">Close</a>
            <button type="button" class="btn btn-green">Save changes</button>
          </div>
        </div>
      </div>
    </div>

  </body>
</html>
