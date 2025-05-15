/**
 * Hut.ke - Main JavaScript File
 * Contains all core functionality for the landing page and shared components
 */

document.addEventListener('DOMContentLoaded', function() {
    // ========== Global Variables ==========
    const body = document.body;
    const html = document.documentElement;

    // ========== Mobile Navigation ==========
    const initMobileNav = () => {
        const mobileNavToggle = document.getElementById('mobileNavToggle');
        const mobileNav = document.getElementById('mobileNav');

        if (mobileNavToggle && mobileNav) {
            mobileNavToggle.addEventListener('click', function(e) {
                e.preventDefault();
                this.classList.toggle('active');
                mobileNav.classList.toggle('active');
                body.classList.toggle('nav-open');
            });
        }
    };

    // ========== Smooth Scrolling ==========
    const initSmoothScroll = () => {
        // Smooth scroll for anchor links
        document.querySelectorAll('a[href^="#"]').forEach(anchor => {
            anchor.addEventListener('click', function(e) {
                e.preventDefault();

                const targetId = this.getAttribute('href');
                if (targetId === '#') return;

                const targetElement = document.querySelector(targetId);
                if (targetElement) {
                    const headerHeight = document.querySelector('header').offsetHeight;
                    const targetPosition = targetElement.getBoundingClientRect().top + window.pageYOffset - headerHeight;

                    window.scrollTo({
                        top: targetPosition,
                        behavior: 'smooth'
                    });

                    // Close mobile menu if open
                    if (body.classList.contains('nav-open')) {
                        document.getElementById('mobileNavToggle').classList.remove('active');
                        document.getElementById('mobileNav').classList.remove('active');
                        body.classList.remove('nav-open');
                    }
                }
            });
        });
    };

    // ========== Scroll Animations ==========
    const initScrollAnimations = () => {
        const animateOnScroll = () => {
            const elements = document.querySelectorAll('.animate-on-scroll');

            elements.forEach(element => {
                const elementPosition = element.getBoundingClientRect().top;
                const windowHeight = window.innerHeight;

                if (elementPosition < windowHeight - 100) {
                    element.classList.add('animated');
                }
            });
        };

        // Initial check
        animateOnScroll();

        // Check on scroll
        window.addEventListener('scroll', animateOnScroll);
    };

    // ========== Form Handling ==========
    const initForms = () => {
        // Newsletter subscription form
        const newsletterForm = document.getElementById('newsletterForm');
        if (newsletterForm) {
            newsletterForm.addEventListener('submit', function(e) {
                e.preventDefault();

                const emailInput = this.querySelector('input[type="email"]');
                const submitBtn = this.querySelector('button[type="submit"]');
                const formData = new FormData(this);

                // Show loading state
                submitBtn.disabled = true;
                submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Subscribing...';

                // Simulate API call
                setTimeout(() => {
                    // Reset form
                    this.reset();

                    // Show success message
                    submitBtn.innerHTML = '<i class="fas fa-check"></i> Subscribed!';

                    // Create and show toast notification
                    showToast('Successfully subscribed to our newsletter!', 'success');

                    // Reset button after delay
                    setTimeout(() => {
                        submitBtn.disabled = false;
                        submitBtn.innerHTML = 'Subscribe';
                    }, 2000);
                }, 1500);
            });
        }

        // Contact form handling
        const contactForm = document.getElementById('contactForm');
        if (contactForm) {
            contactForm.addEventListener('submit', function(e) {
                e.preventDefault();

                const submitBtn = this.querySelector('button[type="submit"]');
                const formData = new FormData(this);

                // Show loading state
                submitBtn.disabled = true;
                submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Sending...';

                // Simulate API call
                setTimeout(() => {
                    // Reset form
                    this.reset();

                    // Show success message
                    submitBtn.innerHTML = '<i class="fas fa-check"></i> Sent!';
                    showToast('Your message has been sent successfully!', 'success');

                    // Reset button after delay
                    setTimeout(() => {
                        submitBtn.disabled = false;
                        submitBtn.innerHTML = 'Send Message';
                    }, 2000);
                }, 2000);
            });
        }
    };

    // ========== Toast Notifications ==========
    const showToast = (message, type = 'info') => {
        const toastContainer = document.getElementById('toastContainer') || createToastContainer();
        const toast = document.createElement('div');

        toast.className = `toast toast-${type}`;
        toast.innerHTML = `
            <div class="toast-content">
                <i class="fas ${getToastIcon(type)}"></i>
                <span>${message}</span>
            </div>
            <button class="toast-close"><i class="fas fa-times"></i></button>
        `;

        toastContainer.appendChild(toast);

        // Auto-remove after delay
        setTimeout(() => {
            toast.classList.add('fade-out');
            setTimeout(() => toast.remove(), 300);
        }, 5000);

        // Manual close
        toast.querySelector('.toast-close').addEventListener('click', () => {
            toast.classList.add('fade-out');
            setTimeout(() => toast.remove(), 300);
        });
    };

    const createToastContainer = () => {
        const container = document.createElement('div');
        container.id = 'toastContainer';
        document.body.appendChild(container);
        return container;
    };

    const getToastIcon = (type) => {
        const icons = {
            'success': 'fa-check-circle',
            'error': 'fa-exclamation-circle',
            'warning': 'fa-exclamation-triangle',
            'info': 'fa-info-circle'
        };
        return icons[type] || 'fa-info-circle';
    };

    // ========== Auth Modal Handling ==========
    const initAuthModals = () => {
        const authModals = {
            'login': document.getElementById('loginModal'),
            'signup': document.getElementById('signupModal')
        };

        // Open modal handlers
        document.querySelectorAll('[data-modal-toggle]').forEach(trigger => {
            trigger.addEventListener('click', function(e) {
                e.preventDefault();
                const modalId = this.getAttribute('data-modal-toggle');
                if (authModals[modalId]) {
                    openModal(authModals[modalId]);
                }
            });
        });

        // Close modal handlers
        document.querySelectorAll('.modal-close, .modal-overlay').forEach(element => {
            element.addEventListener('click', function() {
                closeModal(this.closest('.modal'));
            });
        });

        // Switch between login/signup
        document.querySelectorAll('.auth-switch').forEach(link => {
            link.addEventListener('click', function(e) {
                e.preventDefault();
                const currentModal = this.closest('.modal');
                const targetModalId = this.getAttribute('data-modal-target');

                if (authModals[targetModalId]) {
                    closeModal(currentModal);
                    openModal(authModals[targetModalId]);
                }
            });
        });
    };

    const openModal = (modal) => {
        if (!modal) return;

        body.classList.add('modal-open');
        modal.classList.add('active');

        // Focus first input
        const input = modal.querySelector('input');
        if (input) input.focus();
    };

    const closeModal = (modal) => {
        if (!modal) return;

        body.classList.remove('modal-open');
        modal.classList.remove('active');
    };

    // ========== Password Visibility Toggle ==========
    const initPasswordToggle = () => {
        document.querySelectorAll('.password-toggle').forEach(toggle => {
            toggle.addEventListener('click', function() {
                const input = this.previousElementSibling;
                const icon = this.querySelector('i');

                if (input.type === 'password') {
                    input.type = 'text';
                    icon.classList.remove('fa-eye');
                    icon.classList.add('fa-eye-slash');
                } else {
                    input.type = 'password';
                    icon.classList.remove('fa-eye-slash');
                    icon.classList.add('fa-eye');
                }
            });
        });
    };

    // ========== FAQ Accordion ==========
    const initFAQAccordion = () => {
        const faqItems = document.querySelectorAll('.faq-item');

        faqItems.forEach(item => {
            const question = item.querySelector('.faq-question');

            question.addEventListener('click', () => {
                // Close other open items
                faqItems.forEach(otherItem => {
                    if (otherItem !== item && otherItem.classList.contains('active')) {
                        otherItem.classList.remove('active');
                        otherItem.querySelector('.faq-answer').style.maxHeight = null;
                    }
                });

                // Toggle current item
                item.classList.toggle('active');
                const answer = item.querySelector('.faq-answer');

                if (item.classList.contains('active')) {
                    answer.style.maxHeight = answer.scrollHeight + 'px';
                } else {
                    answer.style.maxHeight = null;
                }
            });
        });
    };

    // ========== Initialize All Functions ==========
    const init = () => {
        initMobileNav();
        initSmoothScroll();
        initScrollAnimations();
        initForms();
        initAuthModals();
        initPasswordToggle();
        initFAQAccordion();

        // Add any additional initialization here
    };

    // Start the application
    init();
});

// ========== Utility Functions ==========
/**
 * Debounce function to limit how often a function can fire
 * @param {Function} func - The function to debounce
 * @param {number} wait - Time to wait in milliseconds
 * @returns {Function}
 */
function debounce(func, wait) {
    let timeout;
    return function() {
        const context = this, args = arguments;
        clearTimeout(timeout);
        timeout = setTimeout(() => func.apply(context, args), wait);
    };
}

/**
 * Throttle function to limit how often a function can fire
 * @param {Function} func - The function to throttle
 * @param {number} limit - Time limit in milliseconds
 * @returns {Function}
 */
function throttle(func, limit) {
    let lastFunc;
    let lastRan;
    return function() {
        const context = this;
        const args = arguments;
        if (!lastRan) {
            func.apply(context, args);
            lastRan = Date.now();
        } else {
            clearTimeout(lastFunc);
            lastFunc = setTimeout(function() {
                if ((Date.now() - lastRan) >= limit) {
                    func.apply(context, args);
                    lastRan = Date.now();
                }
            }, limit - (Date.now() - lastRan));
        }
    };
}