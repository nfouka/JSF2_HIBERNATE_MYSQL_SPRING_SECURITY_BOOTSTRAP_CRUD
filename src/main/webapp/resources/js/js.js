       
    setCarouselHeight('#carousel-example2');
    setCarouselHeight('#carousel-example3');
    setCarouselHeight('#carousel-example1'); 

    function setCarouselHeight(id)
    {
        var slideHeight = [];
        $(id+' .item').each(function()
        {
            // add all slide heights to an array
            slideHeight.push($(this).height());
        });

        // find the tallest item
        max = Math.max.apply(null, slideHeight);

        // set the slide's height
        $(id+' .carousel-content').each(function()
        {
            $(this).css('height',max+'px');
        });
    }
    



    function start() {
        PF('statusDialog').show();
    }
     
    function stop() {
        PF('statusDialog').hide();
    }
