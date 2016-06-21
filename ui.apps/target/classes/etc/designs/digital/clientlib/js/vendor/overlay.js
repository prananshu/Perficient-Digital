(function() {
	var triggerBttn = document.getElementById('search-trigger-overlay'), overlay = document
			.querySelector('div.search-overlay'), otherOverlay = document
			.querySelector('div.contact-overlay'),
	// closeBttn = overlay.querySelector( 'button.search-overlay-close' );
	transEndEventNames = {
		'WebkitTransition' : 'webkitTransitionEnd',
		'MozTransition' : 'transitionend',
		'OTransition' : 'oTransitionEnd',
		'msTransition' : 'MSTransitionEnd',
		'transition' : 'transitionend'
	}, transEndEventName = transEndEventNames[Modernizr.prefixed('transition')], support = {
		transitions : Modernizr.csstransitions
	};

	function toggleOverlay() {
		if (otherOverlay !== null) {
			if (classie.has(otherOverlay, 'open')) {
				classie.remove(otherOverlay, 'open');
				classie.add(otherOverlay, 'close');
				var onEndTransitionFn = function(ev) {

					classie.remove(otherOverlay, 'close');
				};

				onEndTransitionFn();

			}
		}
		if (overlay !== null) {
			if (classie.has(overlay, 'open')) {
				classie.remove(overlay, 'open');
				classie.add(overlay, 'close');
				var onEndTransitionFn = function(ev) {

					classie.remove(overlay, 'close');
				};

				onEndTransitionFn();

			} else if (!classie.has(overlay, 'close')) {
				classie.add(overlay, 'open');
			}
		}
	}
	if (triggerBttn !== null) {
		triggerBttn.addEventListener('click', toggleOverlay);
	}
	// closeBttn.addEventListener( 'click', toggleOverlay );
})();

(function() {
	var triggerBttn = document.getElementById('contact-trigger-overlay');
	var overlay = document.querySelector('div.contact-overlay');
	var otherOverlay = document.querySelector('div.search-overlay');
	var closeBttn = null;
	if (overlay !== null) {
		closeBttn = overlay.querySelector('button.contact-overlay-close');
	}
	transEndEventNames = {
		'WebkitTransition' : 'webkitTransitionEnd',
		'MozTransition' : 'transitionend',
		'OTransition' : 'oTransitionEnd',
		'msTransition' : 'MSTransitionEnd',
		'transition' : 'transitionend'
	},
			transEndEventName = transEndEventNames[Modernizr
					.prefixed('transition')], support = {
				transitions : Modernizr.csstransitions
			};

	function toggleOverlay() {
		if (otherOverlay !== null) {
			if (classie.has(otherOverlay, 'open')) {
				classie.remove(otherOverlay, 'open');
				classie.add(otherOverlay, 'close');
				var onEndTransitionFn = function(ev) {

					classie.remove(otherOverlay, 'close');
				};
				onEndTransitionFn();
			}
		}
		if (overlay !== null) {
			if (classie.has(overlay, 'open')) {
				classie.remove(overlay, 'open');
				classie.add(overlay, 'close');
				var onEndTransitionFn = function(ev) {

					classie.remove(overlay, 'close');
				};
				onEndTransitionFn();
			} else if (!classie.has(overlay, 'close')) {
				classie.add(overlay, 'open');
			}
		}
	}
	if (triggerBttn !== null) {
		triggerBttn.addEventListener('click', toggleOverlay);
	}
	if (closeBttn !== null) {
		closeBttn.addEventListener('click', toggleOverlay);
	}
})();