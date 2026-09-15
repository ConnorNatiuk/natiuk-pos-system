import { searchProduct } from './api/handleSearch.js';
import { setupQuantityControls } from './components/quantityControls.js';

const API_URL = 'http://localhost:8080/api/products';

let currentSubtotal = 0.00;
let currentTax = 0.00;
let currentTotal = 0.00;

const subtotalDisplay = document.getElementById('subtotal-display');
const taxDisplay = document.getElementById('tax-display');
const totalDisplay = document.getElementById('total-display');

const resetButton = document.getElementById('reset-button');

resetButton.addEventListener('click', resetAll);

let productDatabase = [];


async function fetchProductsFromBackend() {
    try {
        const response = await fetch(API_URL)
        if (!response.ok) {
            throw new Error(`Error! ${response.status}`);
        }

        const data = await response.json();

        productDatabase = data.map(product => ({
            id: product.id,
            sku: product.sku,
            name: product.name.toLowerCase(),
            displayName: product.displayname,
            price: Number(product.price),
            quantity: product.quantity,
        }));

    } catch (error) {
        console.error("ERROR: Could not fetch products from backend: ", error)
    }
}

fetchProductsFromBackend();

const searchButton = document.getElementById('search-button');
const searchInput = document.getElementById('search-input');
const displayArea = document.querySelector('.display-area');

// SEARCH BAR AND ITEM HANDLING
function handleSearch() {
    const foundProduct = searchProduct(searchInput.value, productDatabase);
    if (foundProduct) {
        const itemCard = document.createElement('div');
        let itemQuantity = 1;

        itemCard.innerHTML = `
            <div class="item-info">
                <p>SKU #${foundProduct.sku}</p>
                <strong>${foundProduct.displayName}</strong>
                <button class="void-button">Void</button>
                <div class="quantity-control">
                    <button class="qty-btn minus-btn">-</button>
                    <span class="item-quantity">${itemQuantity}</span>
                    <button class="qty-btn plus-btn">+</button>
                </div>
                <strong><span class="price-display">$${foundProduct.price.toFixed(2)}</span></strong>
            </div>
        `;
        displayArea.appendChild(itemCard);
        currentSubtotal += foundProduct.price * itemQuantity;
        updatesTotal();
        
        setupQuantityControls(itemCard, foundProduct, (priceDelta) => {
            currentSubtotal = Math.max(0, currentSubtotal + priceDelta);
            updatesTotal();
        });

        console.log(`Added product: ${foundProduct.name}`)
    } else {
        console.log("item not found");
    }

    searchInput.value = '';
};

function updatesTotal() {
    currentTax = currentSubtotal * 0.08;
    currentTotal = currentSubtotal + currentTax;

    subtotalDisplay.textContent = currentSubtotal.toFixed(2);
    taxDisplay.textContent = currentTax.toFixed(2);
    totalDisplay.textContent = currentTotal.toFixed(2);
}

searchInput.addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {
        handleSearch()
    }
})
searchButton.addEventListener('click', handleSearch);

//

const checkoutButton = document.getElementById('checkout-button');

checkoutButton.addEventListener('click', function() {
    resetAll();
})

function resetAll() {
    currentSubtotal = 0.00;
    currentTax = 0.00;
    currentTotal = 0.00;
    subtotalDisplay.textContent = currentSubtotal.toFixed(2);
    taxDisplay.textContent = currentTax.toFixed(2);
    totalDisplay.textContent = currentTotal.toFixed(2);
    displayArea.innerHTML = ``;
}