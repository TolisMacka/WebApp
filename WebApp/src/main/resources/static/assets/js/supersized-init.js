jQuery(function ($) {

    $.supersized({

        // Features
        slide_interval: 4000,      // length between conversions
        transition: 1,         // 0-none, 1-fade in and fade out, 2-slide top, 3-slide right, 4-slide bottom, 5-slide left, 6-right carousel, 7-left carousel
        transition_speed: 1000,      // Transition speed
        performance: 1,         // 0-normal, 1-mixed speed/quality, 2-better image quality, three excellent conversion speeds // (only for Firefox/IE browser, not Webkit)

        // size and position
        min_width: 0,               // minimum allowable width (in pixels)
        min_height: 0,              // minimum allowable height (in pixels)
        vertical_center: 1,         // center the background vertically
        horizontal_center: 1,       // The background of the horizontal center
        fit_always: 0,              // The image will never exceed the width or height of the browser (ignore min. size)
        fit_portrait: 1,            // The portrait image will not exceed the browser height
        fit_landscape: 0,           // The image of the landscape will not exceed the width of the browser

        // component
        slide_links: 'blank',    // Individual links are for each slide (options: false,'min','name','empty')
        slides: [    // Slideshow image
            { image: 'assets/img/1.jpg' },
            { image: 'assets/img/2.jpg' },
            { image: 'assets/img/3.jpg' }
        ]

    });

});
