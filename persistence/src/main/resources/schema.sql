CREATE TABLE IF NOT EXISTS users
(
    user_id     SERIAL,
    mail        varchar(255),
    password    varchar(255),
    name        varchar(255),
    surname     varchar(255),
    username    varchar(255),
    gender      varchar(20) DEFAULT 'notSpecified',
    user_status varchar(20) DEFAULT 'REGULAR',
    enabled     BOOLEAN DEFAULT FALSE,
    is_admin    BOOLEAN DEFAULT FALSE,
    image       bytea,
    PRIMARY KEY (user_id)
);



CREATE TABLE IF NOT EXISTS ingredients
(
    ingredient_id      SERIAL,
    ingredient_name    varchar(255) DEFAULT NULL,
    is_vegetarian      bool         DEFAULT 'false',
    is_vegan           bool         DEFAULT 'false',
    tacc_free          bool         DEFAULT 'false',
    protein_count      float        DEFAULT NULL,
    calorie_count      float        DEFAULT NULL,
    carbohydrate_count float        DEFAULT NULL,
    fat_count          float        DEFAULT NULL,
    sugar_count        float        DEFAULT NULL,
    serving_type       varchar(255) DEFAULT 'Grams',
    serving            float        DEFAULT NULL,
    ingredient_status  varchar(20)  DEFAULT 'REGULAR',
    user_id            int          DEFAULT NULL,
    PRIMARY KEY (ingredient_id),
    CONSTRAINT ingredients_constraint FOREIGN KEY (user_id) REFERENCES users (user_id)
);


CREATE TABLE IF NOT EXISTS recipes
(
    recipe_id     SERIAL,
    recipe_name   varchar(255),
    user_id       int,
    description   varchar(255),
    instructions  text,
    recipe_status varchar(20)        DEFAULT 'REGULAR',
    rating        float              DEFAULT NULL,
    date_created  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    image         bytea,
    PRIMARY KEY (recipe_id),
    CONSTRAINT recipes_constraint_1 FOREIGN KEY (user_id) REFERENCES users (user_id)
);


CREATE TABLE IF NOT EXISTS recipes_ingredients
(
    recipe_id      int NOT NULL,
    ingredient_id  int NOT NULL,
    obs            varchar(255),
    serving_amount float       DEFAULT NULL,
    ri_status      varchar(20) DEFAULT 'REGULAR',
    PRIMARY KEY (recipe_id, ingredient_id),
    CONSTRAINT recipes_ingredients_constraint_1 FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id),
    CONSTRAINT recipes_ingredients_constraint_2 FOREIGN KEY (ingredient_id) REFERENCES ingredients (ingredient_id)
);

CREATE TABLE IF NOT EXISTS user_ingredients
(
    user_id        int NOT NULL,
    ingredient_id  int NOT NULL,
    obs            varchar(255),
    serving_amount float       DEFAULT NULL,
    ui_status      varchar(20) DEFAULT 'REGULAR',
    PRIMARY KEY (user_id, ingredient_id),
    CONSTRAINT user_ingredients_constraint_1 FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT user_ingredients_constraint_2 FOREIGN KEY (ingredient_id) REFERENCES ingredients (ingredient_id)
);


CREATE TABLE IF NOT EXISTS ratings
(
    user_id   int       NOT NULL,
    recipe_id int       NOT NULL,
    rating    float              DEFAULT NULL,
    date      timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status    varchar(20)        DEFAULT 'REGULAR',
    PRIMARY KEY (user_id, recipe_id),
    CONSTRAINT ratings_constraint_1 FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT ratings_constraint_2 FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)
);

CREATE TABLE IF NOT EXISTS recipe_tags
(
    tag         varchar(20) NOT NULL,
    recipe_id   int         NOT NULL,
    tags_status varchar(20) DEFAULT 'REGULAR',
    PRIMARY KEY (recipe_id, tag),
    CONSTRAINT recipe_tags_constraint FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)


);

CREATE TABLE IF NOT EXISTS user_recipe_list
(
    recipe_list_id SERIAL,
    list_name      VARCHAR(255),
    user_id        INT NOT NULL,
    ur_status      VARCHAR(20) DEFAULT 'REGULAR',
    PRIMARY KEY (recipe_list_id),
    CONSTRAINT recipe_tags_constraint FOREIGN KEY (user_id) REFERENCES users (user_id)

);

CREATE TABLE IF NOT EXISTS recipe_list
(
    recipe_list_id INT NOT NULL,
    recipe_id INT NOT NULL,
    rl_status      VARCHAR(20) DEFAULT 'REGULAR',
    PRIMARY KEY (recipe_list_id,recipe_id),
    CONSTRAINT recipe_list_constraint_1 FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id),
    CONSTRAINT recipe_list_constraint_2 FOREIGN KEY (recipe_list_id) REFERENCES user_recipe_list (recipe_list_id)
);

CREATE TABLE IF NOT EXISTS recipes_comments
(
    comment_id     SERIAL,
    message        text,
    recipe_id      int       NOT NULL,
    user_id        int       NOT NULL,
    comment_status varchar(20)        DEFAULT 'REGULAR',
    comment_date   timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (comment_id),
    CONSTRAINT recipes_comments_constraint_1 FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id),
    CONSTRAINT recipes_comments_constraint_2 FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE IF NOT EXISTS verification_tokens
(
    token_id     SERIAL,
    token        text,
    user_id      int NOT NULL,
    expiry_date  DATE NOT NULL,
    token_status varchar(20) DEFAULT 'REGULAR',
    PRIMARY KEY (token_id),
    CONSTRAINT tokens_constraint FOREIGN KEY (user_id) REFERENCES users (user_id)
);







