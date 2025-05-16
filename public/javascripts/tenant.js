document.addEventListener('DOMContentLoaded', function() {
    // Toggle password visibility
    const togglePassword = document.querySelector('.toggle-password');
    if (togglePassword) {
        togglePassword.addEventListener('click', function() {
            const passwordInput = document.querySelector('#password');
            const icon = this.querySelector('i');

            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            } else {
                passwordInput.type = 'password';
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            }
        });
    }

    // Login form submission
    const loginForm = document.getElementById('tenantLoginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            e.preventDefault();
            // Add login logic here
            window.location.href = 'tenant_dash';
        });
    }

    // Logout button
    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', function(e) {
            e.preventDefault();
            // Add logout logic here
            window.location.href = 'index';
        });
    }

    // Payment modal
    const payRentBtn = document.getElementById('payRentBtn');
    const paymentModal = document.getElementById('paymentModal');
    const closeModal = document.querySelector('.close-modal');

    if (payRentBtn && paymentModal) {
        payRentBtn.addEventListener('click', function() {
            paymentModal.classList.add('active');
        });

        closeModal.addEventListener('click', function() {
            paymentModal.classList.remove('active');
        });

        // Close modal when clicking outside
        window.addEventListener('click', function(e) {
            if (e.target === paymentModal) {
                paymentModal.classList.remove('active');
            }
        });
    }

    // Payment method selection
    const paymentMethods = document.querySelectorAll('.payment-method');
    paymentMethods.forEach(method => {
        method.addEventListener('click', function() {
            paymentMethods.forEach(m => m.classList.remove('active'));
            this.classList.add('active');
        });
    });

    // Payment form submission
    const paymentForm = document.getElementById('paymentForm');
    if (paymentForm) {
        paymentForm.addEventListener('submit', function(e) {
            e.preventDefault();

            // Simulate payment processing
            const submitBtn = this.querySelector('button[type="submit"]');
            submitBtn.disabled = true;
            submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Processing...';

            setTimeout(() => {
                // Simulate successful payment
                submitBtn.innerHTML = '<i class="fas fa-check"></i> Payment Successful';

                // Close modal after delay
                setTimeout(() => {
                    paymentModal.classList.remove('active');
                    submitBtn.disabled = false;
                    submitBtn.innerHTML = 'Initiate Payment';

                    // Show success message (you could implement a toast notification)
                    alert('Payment of KSh 25,000 processed successfully!');
                }, 1500);
            }, 2000);
        });
    }

    // Quick action buttons
    const quickActions = {
        'payRentBtn': () => {
            paymentModal.classList.add('active');
        },
        'requestMaintenance': () => {
            // Redirect to maintenance page
            window.location.hash = '#requests';
        },
        'messageManager': () => {
            // Redirect to messages page
            window.location.hash = '#messages';
        },
        'downloadReceipt': () => {
            // Simulate receipt download
            alert('Receipt downloaded successfully!');
        }
    };

    Object.keys(quickActions).forEach(id => {
        const element = document.getElementById(id);
        if (element) {
            element.addEventListener('click', quickActions[id]);
        }
    });

    // Mobile menu toggle (for responsive design)
    const mobileMenuToggle = document.getElementById('mobileMenuToggle');
    const sidebar = document.querySelector('.sidebar');

    if (mobileMenuToggle && sidebar) {
        mobileMenuToggle.addEventListener('click', function() {
            sidebar.classList.toggle('active');
        });
    }
});