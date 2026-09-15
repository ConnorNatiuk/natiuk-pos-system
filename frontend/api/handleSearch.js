export function searchProduct(query, products) {
    const searchQuery = query.trim().toLowerCase();
    if (!searchQuery) return null;
    return products.find(products => products.name === searchQuery) || null;
}