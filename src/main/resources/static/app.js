// localStorage에서 상품 데이터를 가져오거나 기본 데이터 사용
const products = JSON.parse(localStorage.getItem('menuItems')) || [
  { name: "Columbia Nariñó", price: 5000, category: "커피콩", image: "https://i.imgur.com/HKOFQYa.jpeg", description: "부드럽고 향긋한 커피" },
  { name: "Brazil Serra Do Caparaó", price: 6000, category: "커피콩", image: "https://i.imgur.com/HKOFQYa.jpeg", description: "진하고 깊은 맛을 가진 커피" },
  { name: "Ethiopia Yirgacheffe", price: 5500, category: "커피콩", image: "https://i.imgur.com/HKOFQYa.jpeg", description: "상큼하고 신선한 맛" }
];

const cart = {};

function renderProducts() {
  const productList = document.getElementById("product-list");
  productList.innerHTML = "";
  products.forEach((product, index) => {
    const li = document.createElement("li");
    li.className = "list-group-item d-flex mt-3";
    li.innerHTML = `
      <div class="col-2"><img class="img-fluid" src="${product.image}" alt=""></div>
      <div class="col">
        <div class="row text-muted">${product.category}</div>
        <div class="row">${product.name}</div>
      </div>
      <div class="col text-center price">${product.price}원</div>
      <div class="col text-end action"><button class="btn btn-small btn-outline-dark" onclick="addToCart(${index})">추가</button></div>
    `;
    productList.appendChild(li);
  });
}

function addToCart(index) {
  const product = products[index];
  cart[product.name] = cart[product.name]
    ? { ...product, quantity: cart[product.name].quantity + 1 }
    : { ...product, quantity: 1 };
  updateSummary();
}

function removeFromCart(productName) {
  delete cart[productName];
  updateSummary();
}

function changeQuantity(productName, value) {
  const quantity = parseInt(value);
  if (!isNaN(quantity) && quantity > 0) {
    cart[productName].quantity = quantity;
  } else {
    delete cart[productName];
  }
  updateSummary();
}

function updateSummary() {
  const summaryList = document.getElementById("summary-list");
  const totalPrice = document.getElementById("total-price");
  summaryList.innerHTML = "";
  let total = 0;

  Object.values(cart).forEach(item => {
    total += item.price * item.quantity;
    const row = document.createElement("div");
    row.className = "row justify-content-between align-items-center mb-2";
    row.innerHTML = `
      <div class="col">
        <h6 class="p-0 m-0">${item.name}</h6>
      </div>
      <div class="col-auto">
        <input type="number" class="form-control form-control-sm" min="1" value="${item.quantity}" style="width: 60px;"
          onchange="changeQuantity('${item.name}', this.value)">
      </div>
      <div class="col-auto">
        <button class="btn btn-sm btn-outline-danger" onclick="removeFromCart('${item.name}')">삭제</button>
      </div>
    `;
    summaryList.appendChild(row);
  });

  totalPrice.textContent = `${total.toLocaleString()}원`;
}

window.onload = renderProducts;