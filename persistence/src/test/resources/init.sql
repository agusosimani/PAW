/* USERS */
INSERT INTO users(user_id, username, password, mail, name, surname, gender, user_status)
VALUES (1, 'username1', 'password1', 'email1@email.com', 'name1', 'surname1', 'MALE', 'REGULAR');

INSERT INTO users(user_id, username, password, mail, name, surname, gender, user_status)
VALUES (2, 'username2', 'password2', 'email2@email.com', 'name2', 'surname2', 'MALE', 'REGULAR');


/* INGREDIENTS */
INSERT INTO ingredients(ingredient_id, ingredient_name, is_vegetarian, is_vegan, tacc_free, protein_count, calorie_count,
carbohydrate_count, fat_count, sugar_count, serving_type, serving, ingredient_status, user_id)
VALUES(1, 'ChickenBreast',false,false,true,28.96,191,0,7.57,0,'Grams',98,'REGULAR',1);

INSERT INTO ingredients(ingredient_id, ingredient_name, is_vegetarian, is_vegan, tacc_free, protein_count, calorie_count,
carbohydrate_count, fat_count, sugar_count, serving_type, serving, ingredient_status, user_id)
VALUES(2, 'Salmon',false,false,true,21.62,146,0,5.93,0,'Grams',100,'REGULAR',2);


/* RECIPES */
INSERT INTO recipes(recipe_id, recipe_name, user_id, description, instructions, recipe_status, rating)
VALUES(1,'recipe1',1,'description1','instructions1','REGULAR',5.0);

INSERT INTO recipes(recipe_id, recipe_name, user_id, description, instructions, recipe_status, rating)
VALUES(2,'recipe2',2,'description2','instructions2','REGULAR',5.0);


/* RECIPE INGREDIENTS */
INSERT INTO recipes_ingredients(recipe_id, ingredient_id, obs, serving_amount, ri_status)
VALUES (1, 1, 'obs', 2.0, 'REGULAR');


INSERT INTO recipes_ingredients(recipe_id, ingredient_id, obs, serving_amount, ri_status)
VALUES (2, 2, 'obs', 4.0, 'REGULAR');
