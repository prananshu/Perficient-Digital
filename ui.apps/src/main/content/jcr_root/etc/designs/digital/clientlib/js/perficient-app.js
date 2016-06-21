// Slick Carousel //



$('.carousel-multiple').slick({
  dots: true,
  infinite: true,
  slidesToShow: 3,
  slidesToScroll: 3
});


  
$('.carousel-single').slick({
  dots: true,
  infinite: true,
  speed: 300,
  slidesToShow: 1,
  slidesToScroll: 1
});





testimonialBlocks = function() {
    var width = $(window).width()
    if (width < 768) {
        $(".testimonial-blocks .testimonial").hide();
        $(".testimonial-blocks .testimonial:first-child").show();
    } 
    if (width >= 768 && width < 992) {
        $(".testimonial-blocks .testimonial").hide();
        $(".testimonial-blocks .testimonial:nth-of-type(-n+2)").show();
    } 

    if (width >= 992) {
        $(".testimonial-blocks .testimonial").show();
    }
}

$(document).ready(testimonialBlocks);
$(window).resize(testimonialBlocks);





//Controls loading of more Cards in Cards component as well as it's responsiveness
respCards = function() {
    var width = $(window).width()
    if (width >= 992) {
        $(".cards-section:not(.expanded) .card-group .card").hide();
        $(".cards-section:not(.expanded) .card-group .card:nth-of-type(-n+4)").show();
    } 

    else {
        $(".cards-section:not(.expanded) .card-group .card").hide();
        $(".cards-section:not(.expanded) .card-group .card:nth-of-type(-n+3)").show();
    }
    $(".cardsbutton").click(function(e){
        var cardsContainer = $(this).closest(".cards-section").attr("id");
        e.preventDefault();
        $("#" + cardsContainer + " .card-group .card:hidden").slideDown( "slow", function() {
            // Animation complete.
        });
        $("#" + cardsContainer + " .button-wrapper").fadeOut();
    });
    
    $(".cards-section:not(.static) .button-wrapper").show();
}

$(document).ready(respCards);
$(window).resize(respCards);

//sets equal heights to all Cards
equalheight = function(cardContainer){
  var currentTallest = 0,
       currentRowStart = 0,
       rowDivs = new Array(),
       $el,
       topPosition = 0;

  // Reset any heights already assigned to each item
  $(cardContainer).each(function() {
    $(this).height('auto');
  });

  $(cardContainer).each(function() {
    $el = $(this);

    topPostion = $el.position();

    // Start new row, resetting currentTallest on each and emptying row array
    if(currentRowStart != topPostion.top) {
      currentRowStart = topPostion.top;
      currentTallest = 0;
      rowDivs.length = 0;
    }

    rowDivs.push($el);

    // Find tallest item in the row
    if(currentTallest < $el.height()) {
      currentTallest = $el.height();
    }

    for (currentDiv = 0 ; currentDiv < rowDivs.length ; currentDiv++) {
       rowDivs[currentDiv].height(currentTallest);
    }

  });

}

$(window).on('load resize',function() {
  equalheight('.cards-section:not(.eventpanels) .card-group .card:not(.expanded)');
  equalheight('.card-group .card:not(.expanded) .card-content .event-table .event-row .event-details');
  equalheight('.card-group .card:not(.expanded) .card-content .event-table .event-row .event-host');
  equalheight('.panel-wrapper .card');
  equalheight('.panel-wrapper .card .event');
  equalheight('.panel-wrapper .card .card-footer');
  equalheight('.award-card h1');
  equalheight('.award-card .award-card-content');
});

//Adds load more functionality to Tiles component
loadTiles = function() {
    
        $(".tiles-row.hidden").hide();
        $(".tiles-row").slice(0, 1).show();
   
    
    $(".tiles-load-button").click(function(e){
        var tilesContainer = $(this).closest(".tiles-wrapper").attr("id");
        e.preventDefault();
        $("#" + tilesContainer + " .tiles-row:hidden").slideDown( "slow", function() {
            // Animation complete.
        });
        $("#" + tilesContainer + " .button-wrapper").fadeOut();
    });
    $(".tiles-section .button-wrapper").show();
}

$(document).ready(loadTiles);
$(window).resize(loadTiles);

//Adds load more functionality to cta-squares component
loadSquares = function() {
    
    var width = $(window).width()
    if (width < 992) {
        $(".square-wrapper .square-container").hide();
        $(".square-wrapper .square-container").slice(0, 4).show();
        $(".cta-squares .button-wrapper").show();
    } 

    else {
        $(".square-wrapper .square-container").show();
        $(".cta-squares .button-wrapper").hide();
    }
    
    $(".squaresbutton").click(function(e){
        var squaresContainer = $(this).closest(".cta-squares").attr("id");
        e.preventDefault();
        $("#" + squaresContainer + " .square-wrapper .square-container:hidden").slideDown( "slow", function() {
            // Animation complete.
        });
        $("#" + squaresContainer + " .button-wrapper").fadeOut();
    });
}

$(document).ready(loadSquares);
$(window).resize(loadSquares);

//*******
$('.btn-hero-menu').click(function(){
  $('.hero-slide-menu').toggleClass('on');
  $('.btn-hero-menu').toggleClass('on');
});


$('.dropdown-toggle').dropdown();

$( ".search-icon" ).click(function() {
  $(".search-icon").toggleClass("shown");
});

$( ".mobile-open" ).click(function() {
  $(".mobile-open").parent().toggleClass("open");
});

$("#contact-trigger-overlay").on("click", function() {
  $(".search-icon").removeClass("shown");
});


//Video Overlay functionality 
$(window).on('load resize',function() {
  var width = $(window).width()
  
  $('.hero-video .play-button').click(function(){
    $(".hero-video .video-overlay .hero-video").attr('src', 'http://www.youtube.com/embed/oHg5SJYRHA0?autoplay=1');
    $(".hero-video .video-overlay").fadeIn();
  });
  
  $('.hero-video .video-overlay .video-overlay-close').click(function(){
    $(".hero-video .video-overlay .hero-video").attr('src', '');
    $(".hero-video .video-overlay").fadeOut();
  });
});

//Stops modal-embedded YouTube video on Modal Close
$(document).ready(function(){
    $('.modal').each(function(){
            var src = $(this).find('iframe').attr('src');

        $(this).on('click', function(){

            $(this).find('iframe').attr('src', '');
            $(this).find('iframe').attr('src', src);

        });
    });
});






// filter tabs/accordion functionality
$(".tab_content").hide();

/* if in tab mode */
$(".tabs-filter ul.tabs li").click(function() {

  if ( $(this).hasClass( "active" ) ) {
    $(".tabs-filter .tab_content").slideUp();
    $(".tabs-filter ul.tabs li").removeClass("active");
    $(".tabs-filter .tab_drawer_heading").removeClass("d_active");
  }

  else {
    $(".tabs-filter .tab_content").hide();
    var activeTab = $(this).attr("rel");

    if ($(".tabs-filter ul.tabs li.active")[0]){
      $("#"+activeTab).fadeIn();
    }
    else {
      $("#"+activeTab).slideDown();
    }

    $(".tabs-filter ul.tabs li").removeClass("active");
    $(this).addClass("active");

    $(".tabs-filter .tab_drawer_heading").removeClass("d_active");
    $(".tabs-filter .tab_drawer_heading[rel^='"+activeTab+"']").addClass("d_active");
  }
});
/* if in accordion mode */
$(".tabs-filter .tab_drawer_heading").click(function() {

  if ( $(this).hasClass( "d_active" ) ) {
    $(".tabs-filter .tab_content").slideUp();
    $(".tabs-filter .tab_drawer_heading").removeClass("d_active");
    $(".tabs-filter ul.tabs li").removeClass("active");
  }

  else {
    $(".tabs-filter .tab_content").slideUp();
    var d_activeTab = $(this).attr("rel"); 
    $(".tabs-filter #"+d_activeTab).slideDown();

    $(".tabs-filter .tab_drawer_heading").removeClass("d_active");
    $(this).addClass("d_active");

    $(".tabs-filter ul.tabs li").removeClass("active");
    $(".tabs-filter ul.tabs li[rel^='"+d_activeTab+"']").addClass("active");
  }
});

$('.tabs-filter ul.tabs li').last().addClass("tab_last");

$(window).on('load resize',function() {
  var width = $(window).width()
  if (width < 992) {
    $(".tabs-filter .filter-slider").show();
    $(".tabs-filter .tab_container").hide();
  } 

  else {
    $(".tabs-filter .filter-slider").hide();
    $(".tabs-filter .tab_container").show();
  }
});

$(".tabs-filter .filter-slider").click(function() {
      
  if ( $(this).hasClass( "expanded" ) ) {
    $(".tabs-filter .tab_container").slideUp();
    $(".tabs-filter .filter-slider").removeClass("expanded");
  }

  else {
    $(".tabs-filter .tab_container").slideDown();
    $(".tabs-filter .filter-slider").addClass("expanded");
  }

});

stripDesktopDropdown = function() {
  if($(window).width() > 768) {

    $(".main-nav a.nav-link").each(function() {
      $(this).on("click", function() {
        window.location=this.href;
      });
      
    });
  }
}

$(document).ready(stripDesktopDropdown);



  function initMap() {
    var styles = [
    {
        "featureType": "landscape",
        "stylers": [
            {
                "saturation": -100
            },
            {
                "lightness": 65
            },
            {
                "visibility": "on"
            }
        ]
    },
    {
        "featureType": "poi",
        "stylers": [
            {
                "saturation": -100
            },
            {
                "lightness": 51
            },
            {
                "visibility": "simplified"
            }
        ]
    },
    {
        "featureType": "road.highway",
        "stylers": [
            {
                "saturation": -100
            },
            {
                "visibility": "simplified"
            }
        ]
    },
    {
        "featureType": "road.arterial",
        "stylers": [
            {
                "saturation": -100
            },
            {
                "lightness": 30
            },
            {
                "visibility": "on"
            }
        ]
    },
    {
        "featureType": "road.local",
        "stylers": [
            {
                "saturation": -100
            },
            {
                "lightness": 40
            },
            {
                "visibility": "on"
            }
        ]
    },
    {
        "featureType": "transit",
        "stylers": [
            {
                "saturation": -100
            },
            {
                "visibility": "simplified"
            }
        ]
    },
    {
        "featureType": "administrative.province",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "labels",
        "stylers": [
            {
                "visibility": "on"
            },
            {
                "lightness": -25
            },
            {
                "saturation": -100
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "geometry",
        "stylers": [
            {
                "hue": "#ffff00"
            },
            {
                "lightness": -25
            },
            {
                "saturation": -97
            }
        ]
    }
];

    
    var mapDiv = document.getElementById('map');
    var myLatLng = {lat: 44.98023247899551, lng: -93.27526594806245};
    var map = new google.maps.Map(mapDiv, {
      center: myLatLng,
      zoom: 17,
      disableDefaultUI: true,
      styles: styles
    });

    var marker = new google.maps.Marker({
      position: myLatLng,
      map: map
    });
  }

 

$(".profile-overlay .profile-overlay-wrapper .overlay-block").hide();
$(".profile-overlay .profile-overlay-wrapper .profile-block").click(function() {
  $(".profile-overlay .profile-overlay-wrapper .overlay-block").fadeOut();
  $(this).siblings(".profile-overlay .profile-overlay-wrapper .overlay-block").fadeIn();
  setTimeout(function(){
    $("body").addClass("overlay-open");
  },1);
});
$( document ).on( "click", ".overlay-open", function(e) {
  if ($(e.target).closest(".profile-overlay .profile-overlay-wrapper .overlay-block .content-container").length === 0) {
    $(".profile-overlay .profile-overlay-wrapper .overlay-block").fadeOut();
    setTimeout(function(){
      $("body").removeClass("overlay-open");
    },1);
  }
});

jQuery(function($) {
    var $anchors = $('.list a'),
    $items = $('.partners-overlay');

    $anchors.on('click', function() {
        var selectedIndex = $anchors.index(this);
        $items.removeClass('open').eq(selectedIndex).addClass('open');
    });
    $('.close').on('click', function () {
      $items.removeClass('open')
    });
});

(function($) {
  var breakpoint = 768,
      targetExt,
      $container = $('.hero-background-container');
      $heroimage = $('.hero-background');
  
  function loaded() {
    $heroimage.addClass('large-loaded');
  }
  
  // do your thing
  function swapImages() {

      var largeSuffix = '-lg';
      var smallSuffix = '-sm';
    // above breakpoint, we want to show the large image, vice versa
    targetExt = window.innerWidth >= breakpoint ? largeSuffix : smallSuffix;
    
    // grab each image in the hero
    $container.find('img.hero-background').each(function () {
        var url = this.src.split('?')[0];
        var nameWithoutExtension = url.substr(0, this.src.lastIndexOf('.'));
        if ((endsWith(nameWithoutExtension, largeSuffix) || endsWith(nameWithoutExtension, smallSuffix)) && !endsWith(nameWithoutExtension, targetExt)) {
            var currentExt = nameWithoutExtension.slice(-3);
          //var currentExt = this.src.slice(-7);
          // only swap the image if the current extension
          // is not the target extension
            this.src = this.src.replace(currentExt, targetExt);

            // wait for the large version of the image to be loaded
            if (this.complete) {
                loaded();
            } else {
                this.addEventListener('load', loaded);
            }
        }
    });
  }

  $.advancedHero = {
      "slides" : [],
      heroElement : null,
      init : function(element) {
          this.heroElement = $(element);
          $(this.heroElement).find('.hero-background-container').addClass('loading');
          //this.buildNav();
          this.binders();

          if ($.cookie('ranonce')) {
              //subsuquent loads will load a random item in the list
              var rand = Math.floor((Math.random() * $.advancedHero.slides.length) + 1);
              $(this.heroElement).find('.slide-nav li:nth-child(' + rand + ') a').click();
          } else {
              //on the first load, always load the first item
              $(this.heroElement).find('.slide-nav li:first-child a').click();
          }

          //set the cookie so we know we've loaded this component already
          $.cookie('ranonce', true);
      },
      buildNav : function() {
          for(var i = 0; i < $.advancedHero.slides.length; i++) {
              var obj = $.advancedHero.slides[i];
              $.advancedHero.heroElement.find('.slide-nav').append('<li data-slideid="' + i + '"><a href="#">' + obj.heading + '</a></li>');
          }
      },
      handleLoad : function() {
          if(!$.advancedHero.heroElement.find('.hero-background-container').hasClass('loading')) {
              $.advancedHero.heroElement.find('.hero-background-container').addClass('loading');
          }
      },
      binders : function() {
          var slideNav = $(this.heroElement).find('.slide-nav');
          slideNav.children('li').bind("click", function() {
                $.advancedHero.handleLoad();
                
                //get the slide data
                var obj = $.advancedHero.slides[$(this).data("slideid")];

                //change background image
                var dynamicImg = new Image();
                dynamicImg.onload = function() {
                    $.advancedHero.heroElement.find('.hero-background').attr('src', dynamicImg.src);

                    //change background color
                    $.advancedHero.heroElement.removeClass();
                    $.advancedHero.heroElement.addClass("hero");
                    $.advancedHero.heroElement.addClass("hero-block");
                    if (obj.contentBgColor != "")
                        $.advancedHero.heroElement.addClass(obj.contentBgColor);
                    $.advancedHero.heroElement.find('.icon-blockangle').css({ "color": "" });
                    if (obj.contentHexColor != "")
                        $.advancedHero.heroElement.find('.icon-blockangle').css({ "color": "#" + obj.contentHexColor });

                    //set texture image
                    if (obj.textureImage != "")
                        $.advancedHero.heroElement.find('.color-container').css({ "background-image": "url(" + obj.textureImage + ")" });

                    //clear out the contents
                    $.advancedHero.heroElement.find('.hero-content').html("");
                    $.advancedHero.heroElement.find('.hero-caption').html("");

                    //add in heading
                    if (obj.heading != "")
                        $.advancedHero.heroElement.find('.hero-content').append("<h1>" + obj.heading + "</h1>");

                    //add content
                    if (obj.content != "")
                        $.advancedHero.heroElement.find('.hero-content').append(obj.content);

                    //add button link
                    if (obj.buttonActionURL != "" && obj.buttonText != "")
                        $.advancedHero.heroElement.find('.hero-content').append("<a href='" + obj.buttonActionURL + "' class='btn-hero'>" + obj.buttonText + "</a>");

                    //add caption
                    if (obj.caption != "")
                        $.advancedHero.heroElement.find('.hero-caption').html(obj.caption);

                    //clean up
                    $.advancedHero.heroElement.find('.hero-background-container').removeClass('loading');
                    $.advancedHero.heroElement.show();
                }
                dynamicImg.src = obj.slideImage;
              
                //stop default click handler
                return false;
          });
      }
  }

  // init on window load and resize
  $(window).on('load resize', swapImages);
}(jQuery));


// Perficient Digital scroll animation JS
$(document).ready(function() {
  var element = document.getElementsByClassName("grid-second-col");
  $(element).addClass('fade-in');

  $(window).scroll(function() {
    if( $(".grid-second-col").length > 0 ) {
      var elementTopToPageTop = $(element).offset().top;
      var windowTopToPageTop = $(window).scrollTop();
      var windowInnerHeight = window.innerHeight;
      var elementTopToWindowTop = elementTopToPageTop - windowTopToPageTop;
      var elementTopToWindowBottom = windowInnerHeight - elementTopToWindowTop;
      var distanceFromBottomToAppear = 300;

      if(elementTopToWindowBottom > distanceFromBottomToAppear) {
        $(element).addClass('js-fade-show');
      }
      else if(elementTopToWindowBottom < 0) {
        $(element).removeClass('js-fade-show');
        $(element).addClass('js-fade-hide');
      }
    }
  });
});

function endsWith(str, suffix) {
    return str.indexOf(suffix, str.length - suffix.length) !== -1;
}

// temporary fix to remove quotes from leaders if empty. TODO: Remove this once it's properly fixed (and we're no longer inserting HTML tags in C#)
$(document).ready(function() {
    $('.profile-overlay-wrapper blockquote p').filter(function() {
            return $.trim($(this).text()) === '' && $(this).children().length == 0;
        })
        .remove();
});