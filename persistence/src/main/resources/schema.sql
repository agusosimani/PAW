

CREATE TABLE IF NOT EXISTS recipes_comments (
                                comment_id SERIAL,
                                text text,
                                recipe_id int DEFAULT NULL,
                                user_id int DEFAULT NULL,
                                parent_id int DEFAULT NULL,
                                PRIMARY KEY (comment_id)
);



CREATE TABLE IF NOT EXISTS serving_types (
                             serving_type_id SERIAL,
                             name varchar(255) DEFAULT NULL,
                             PRIMARY KEY (serving_type_id)
);


CREATE TABLE IF NOT EXISTS users (
                     user_id SERIAL,
                     mail varchar(255) DEFAULT NULL,
                     password varchar(255) DEFAULT NULL,
                     name varchar(255) DEFAULT NULL,
                     surname varchar(255) DEFAULT NULL,
                     username varchar(255) DEFAULT NULL,
                     gender bit(1) DEFAULT NULL,
                     status int DEFAULT '0',
                     PRIMARY KEY (user_id)
);



CREATE TABLE IF NOT EXISTS ingredients (
                           ingredient_id SERIAL,
                           name varchar(255) DEFAULT NULL,
                           is_vegetarian bit(1) DEFAULT NULL,
                           is_vegan bit(1) DEFAULT NULL,
                           tacc_free bit(1) DEFAULT NULL,
                           protein_count float DEFAULT NULL,
                           calorie_count float DEFAULT NULL,
                           fat_count float DEFAULT NULL,
                           sugar_count float DEFAULT NULL,
                           serving_type_id int DEFAULT NULL,
                           serving float DEFAULT NULL,
                           status int NOT NULL DEFAULT '1',
                           user_id int DEFAULT NULL,
                           PRIMARY KEY (ingredient_id),
                           CONSTRAINT ingredients_ibfk_1 FOREIGN KEY (serving_type_id) REFERENCES serving_types (serving_type_id),
                           CONSTRAINT ingredients_ibfk_2 FOREIGN KEY (user_id) REFERENCES users (user_id)
) ;


CREATE TABLE IF NOT EXISTS recipes (
                       recipe_id SERIAL,
                       name varchar(255) DEFAULT NULL,
                       user_id int DEFAULT NULL,
                       description varchar(255) DEFAULT NULL,
                       instructions text,
                       status int NOT NULL DEFAULT '1',
                       rating float default '0',
                       PRIMARY KEY (recipe_id),
                       CONSTRAINT recipes_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (user_id),
                       CONSTRAINT recipes_ibfk_2 FOREIGN KEY (user_id) REFERENCES users (user_id)
) ;


CREATE TABLE IF NOT EXISTS recipes_ingredients (
                                   recipe_id int NOT NULL,
                                   ingredient_id int NOT NULL,
                                   obs varchar(255) DEFAULT NULL,
                                   serving_amount float DEFAULT NULL,
                                   PRIMARY KEY (recipe_id,ingredient_id),
                                   CONSTRAINT recipes_ingredients_ibfk_1 FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id),
                                   CONSTRAINT recipes_ingredients_ibfk_2 FOREIGN KEY (ingredient_id) REFERENCES ingredients (ingredient_id)
) ;

CREATE TABLE IF NOT EXISTS user_ingredients (
                                user_id int NOT NULL,
                                ingredient_id int NOT NULL,
                                obs varchar(255) DEFAULT NULL,
                                serving_amount float DEFAULT NULL,
                                PRIMARY KEY (user_id,ingredient_id),
                                CONSTRAINT user_ingredients_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (user_id),
                                CONSTRAINT user_ingredients_ibfk_2 FOREIGN KEY (ingredient_id) REFERENCES ingredients (ingredient_id)
) ;


CREATE TABLE IF NOT EXISTS ratings (
                       user_id int NOT NULL,
                       recipe_id int NOT NULL,
                       rating float DEFAULT NULL,
                       date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       status int NOT NULL DEFAULT '1',
                       PRIMARY KEY (user_id,recipe_id),
                       CONSTRAINT ratings_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (user_id),
                       CONSTRAINT ratings_ibfk_2 FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)
) ;

/*TODO: agregarlas a los DAOS mareo, cosas copadas para agregar a la db*/

CREATE TABLE IF NOT EXISTS tags (
                       tag_id SERIAL,
                       name VARCHAR(255),
                       PRIMARY KEY (tag_id)
) ;

CREATE TABLE IF NOT EXISTS recipe_tags (
                       tag_id int NOT NULL,
                       recipe_id int NOT NULL,
                       status int NOT NULL DEFAULT '1',
                       PRIMARY KEY (recipe_id,tag_id),
                       CONSTRAINT recipe_tags_ibfk_1 FOREIGN KEY (tag_id) REFERENCES tags (tag_id),
                       CONSTRAINT recipe_tags_ibfk_2 FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)


) ;




