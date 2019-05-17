function add_rate(clicked_id){
    let searchParams = new URLSearchParams(window.location.search);
    $.post("/rate_recipe", {rate: clicked_id, recipeId: searchParams.get("recipeId")});
    location.reload();
};