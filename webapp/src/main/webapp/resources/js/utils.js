function add_rate(clicked_id){
    let searchParams = new URLSearchParams(window.location.search);
    $.post("/rate_recipe", {rate: clicked_id, recipeId: searchParams.get("recipeId")});
    // $.ajax({
    //     url: "/rate_recipe",
    //     data: {rate: clicked_id, recipeId: searchParams.get("recipeId")},
    //     type: "POST",
    // });
    location.reload();
};