/* hsqldb */
CREATE TABLE IF NOT EXISTS users
(
    user_id     INTEGER IDENTITY PRIMARY KEY,
    mail        varchar(255),
    password    varchar(255),
    name        varchar(255),
    surname     varchar(255),
    username    varchar(255),
    gender      varchar(20) DEFAULT 'notSpecified',
    user_status varchar(20) DEFAULT 'REGULAR',
    image       blob
);


CREATE TABLE IF NOT EXISTS ingredients
(
    ingredient_id      INTEGER IDENTITY PRIMARY KEY,
    ingredient_name    varchar(255) DEFAULT NULL,
    is_vegetarian      boolean      DEFAULT 'false',
    is_vegan           boolean      DEFAULT 'false',
    tacc_free          boolean      DEFAULT 'false',
    protein_count      float        DEFAULT NULL,
    calorie_count      float        DEFAULT NULL,
    carbohydrate_count float        DEFAULT NULL,
    fat_count          float        DEFAULT NULL,
    sugar_count        float        DEFAULT NULL,
    serving_type       varchar(255) DEFAULT 'Grams',
    serving            float        DEFAULT NULL,
    ingredient_status  varchar(20)  DEFAULT 'REGULAR',
    user_id            int          DEFAULT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);


CREATE TABLE IF NOT EXISTS recipes
(
    recipe_id     INTEGER IDENTITY PRIMARY KEY,
    recipe_name   varchar(255),
    user_id       int,
    description   varchar(255),
    instructions  varchar(1000000),
    recipe_status varchar(20)        DEFAULT 'REGULAR',
    rating        float              DEFAULT NULL,
    date_created  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    image         blob,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
) ;


CREATE TABLE IF NOT EXISTS recipes_ingredients
(
    recipe_id      int NOT NULL,
    ingredient_id  int NOT NULL,
    obs            varchar(255),
    serving_amount float       DEFAULT NULL,
    ri_status      varchar(20) DEFAULT 'REGULAR',
    PRIMARY KEY (recipe_id, ingredient_id),
    FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredients (ingredient_id)
) ;


CREATE TABLE IF NOT EXISTS user_ingredients
(
    user_id        int NOT NULL,
    ingredient_id  int NOT NULL,
    obs            varchar(255),
    serving_amount float       DEFAULT NULL,
    ui_status      varchar(20) DEFAULT 'REGULAR',
    PRIMARY KEY (user_id, ingredient_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredients (ingredient_id)
) ;


CREATE TABLE IF NOT EXISTS ratings
(
    user_id   int       NOT NULL,
    recipe_id int       NOT NULL,
    rating    float              DEFAULT NULL,
    date      timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status    varchar(20)        DEFAULT 'REGULAR',
    PRIMARY KEY (user_id, recipe_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)
);


CREATE TABLE IF NOT EXISTS recipe_tags
(
    tag         varchar(20) NOT NULL,
    recipe_id   int         NOT NULL,
    tags_status varchar(20) DEFAULT 'REGULAR',
    PRIMARY KEY (recipe_id, tag),
    FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)
);


CREATE TABLE IF NOT EXISTS user_recipe_list
(
    recipe_list_id INTEGER IDENTITY PRIMARY KEY,
    list_name      VARCHAR(255),
    user_id        INT NOT NULL,
    ur_status      VARCHAR(20) DEFAULT 'REGULAR',
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);


CREATE TABLE IF NOT EXISTS recipe_list
(
    recipe_list_id INT NOT NULL,
    recipe_id      INT NOT NULL,
    rl_status      VARCHAR(20) DEFAULT 'REGULAR',
    PRIMARY KEY (recipe_list_id,recipe_id),
    FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id),
    FOREIGN KEY (recipe_list_id) REFERENCES user_recipe_list (recipe_list_id)
);


CREATE TABLE IF NOT EXISTS recipes_comments
(
    comment_id     INTEGER IDENTITY PRIMARY KEY,
    message        varchar(1000000),
    recipe_id      int       NOT NULL,
    user_id        int       NOT NULL,
    comment_status varchar(20)        DEFAULT 'REGULAR',
    comment_date   timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);