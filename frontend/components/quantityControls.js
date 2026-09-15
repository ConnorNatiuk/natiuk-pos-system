export function setupQuantityControls(cardItem, product, onSubtotalChange) {
    let itemQuantity = 1;

    const plusQuantityButton = cardItem.querySelector('.plus-btn');
    const minusQuantityButton = cardItem.querySelector('.minus-btn');
    const quantityDisplay = cardItem.querySelector('.item-quantity');
    const priceDisplay = cardItem.querySelector('.price-display');
    const voidButton = cardItem.querySelector('.void-button');

    function updateQuantityView() {
        quantityDisplay.textContent = itemQuantity;
        priceDisplay.textContent = `$${(product.price * itemQuantity).toFixed(2)}`;
    }
    
    plusQuantityButton.addEventListener('click', function() {
        itemQuantity++;
        updateQuantityView();
        if (onSubtotalChange) {
            onSubtotalChange(product.price);
        }
    });

    minusQuantityButton.addEventListener('click', function() {
        if (itemQuantity > 1) {
            itemQuantity--;
            updateQuantityView();
            if (onSubtotalChange) {
                onSubtotalChange(-product.price);
            }
        }
    });

    voidButton.addEventListener('click', function() {
        cardItem.remove();
        if (onSubtotalChange) {
            onSubtotalChange(-(product.price * itemQuantity));
        }
    });
}