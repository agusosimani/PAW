/* hsqldb */
DROP TABLE IF EXISTS users;
CREATE TABLE users (
                     user_id INTEGER IDENTITY PRIMARY KEY,
                     mail varchar(255) DEFAULT NULL,
                     password varchar(255) DEFAULT NULL,
                     name varchar(255) DEFAULT NULL,
                     surname varchar(255) DEFAULT NULL,
                     username varchar(255) DEFAULT NULL,
                     gender bit(1) DEFAULT NULL,
                     status int DEFAULT '0'
);


DROP TABLE IF EXISTS serving_types;
CREATE TABLE serving_types (
                             serving_type_id INTEGER IDENTITY PRIMARY KEY,
                             name varchar(255) DEFAULT NULL
);


DROP TABLE IF EXISTS recipes_comments;
CREATE TABLE recipes_comments (
                                comment_id INTEGER IDENTITY PRIMARY KEY,
                                text varchar(1000000),
                                recipe_id int DEFAULT NULL,
                                user_id int DEFAULT NULL,
                                parent_id int DEFAULT NULL
);

DROP TABLE IF EXISTS ingredients;
CREATE TABLE ingredients (
                           ingredient_id INTEGER IDENTITY PRIMARY KEY,
                           name varchar(255) DEFAULT NULL,
                           is_vegetarian bit(1) DEFAULT NULL,
                           is_vegan bit(1) DEFAULT NULL,
                           tacc_free bit(1) DEFAULT NULL,
                           protein_count float DEFAULT NULL,
                           calorie_count float DEFAULT NULL,
                           fat_count float DEFAULT NULL,
                           sugar_count float DEFAULT NULL,
                           serving_type_id INTEGER DEFAULT NULL,
                           serving float DEFAULT NULL,
                           status int DEFAULT '1',
                           user_id int DEFAULT NULL,
                           FOREIGN KEY (user_id) REFERENCES users(user_id),
                           FOREIGN KEY (serving_type_id) REFERENCES serving_types(serving_type_id)
);

DROP TABLE IF EXISTS recipes;
CREATE TABLE recipes (
                       recipe_id INTEGER IDENTITY PRIMARY KEY,
                       name varchar(255) DEFAULT NULL,
                       user_id int DEFAULT NULL,
                       description varchar(255) DEFAULT NULL,
                       instructions varchar(1000000),
                       status int DEFAULT '0',
                       FOREIGN KEY (user_id) REFERENCES users(user_id)
) ;

DROP TABLE IF EXISTS recipes_ingredients;
CREATE TABLE recipes_ingredients (
                                   recipe_id int NOT NULL,
                                   ingredient_id int NOT NULL,
                                   obs varchar(255) DEFAULT NULL,
                                   serving_amount float DEFAULT NULL,
                                   PRIMARY KEY (recipe_id,ingredient_id),
                                   FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id),
                                   FOREIGN KEY (ingredient_id) REFERENCES ingredients (ingredient_id)
) ;

DROP TABLE IF EXISTS user_ingredients;
CREATE TABLE user_ingredients (
                                user_id int NOT NULL,
                                ingredient_id int NOT NULL,
                                obs varchar(255) DEFAULT NULL,
                                serving_amount float DEFAULT NULL,
                                PRIMARY KEY (user_id,ingredient_id),
                                FOREIGN KEY (user_id) REFERENCES users (user_id),
                                FOREIGN KEY (ingredient_id) REFERENCES ingredients (ingredient_id)
) ;

DROP TABLE IF EXISTS ratings;
CREATE TABLE ratings (
                       user_id int NOT NULL,
                       recipe_id int NOT NULL,
                       rating float DEFAULT NULL,
                       date timestamp DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (user_id,recipe_id),
                       FOREIGN KEY (user_id) REFERENCES users (user_id),
                       FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)
) ;