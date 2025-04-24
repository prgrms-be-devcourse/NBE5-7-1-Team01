// localStorage에서 메뉴 아이템을 가져오거나 기본 데이터 사용
let menuItems = JSON.parse(localStorage.getItem('menuItems')) || [
  { name: "Columbia Nariñó", price: 5000, category: "커피콩", image: "https://i.imgur.com/HKOFQYa.jpeg", description: "부드럽고 향긋한 커피" },
  { name: "Brazil Serra Do Caparaó", price: 6000, category: "커피콩", image: "https://i.imgur.com/HKOFQYa.jpeg", description: "진하고 깊은 맛을 가진 커피" },
  { name: "Ethiopia Yirgacheffe", price: 5500, category: "커피콩", image: "https://i.imgur.com/HKOFQYa.jpeg", description: "상큼하고 신선한 맛" }
];

const orders = [
  {
    email: "user1@example.com",
    orderDate: "2025-04-23 13:45:00",
    address: "서울특별시 강남구 역삼동",
    postCode: "06234",
    items: [
      { name: "Columbia Nariñó", quantity: 1 },
      { name: "Brazil Serra Do Caparaó", quantity: 2 }
    ],
    total: 15000
  },
  {
    email: "user2@example.com",
    orderDate: "2025-04-23 10:30:00",
    address: "서울특별시 송파구 잠실동",
    postCode: "05557",
    items: [
      { name: "Ethiopia Yirgacheffe", quantity: 1 },
      { name: "Columbia Nariñó", quantity: 1 }
    ],
    total: 12000
  },
  {
    email: "user3@example.com",
    orderDate: "2025-04-23 08:00:00",
    address: "서울특별시 강서구 화곡동",
    postCode: "07524",
    items: [
      { name: "Brazil Serra Do Caparaó", quantity: 3 }
    ],
    total: 18000
  },
  {
      email: "user4@example.com",
      orderDate: "2025-04-23 15:45:00",
      address: "서울특별시 강남구 논현동",
      postCode: "06245",
      items: [
        { name: "Columbia Nariñó", quantity: 2 },
        { name: "Brazil Serra Do Caparaó", quantity: 1 }
      ],
      total: 16000
  },
  {
      email: "user5@example.com",
      orderDate: "2025-04-23 17:30:00",
      address: "서울특별시 송파구 문정동",
      postCode: "05767",
      items: [
        { name: "Ethiopia Yirgacheffe", quantity: 2 },
        { name: "Columbia Nariñó", quantity: 1 }
      ],
      total: 14500
    },
    {
      email: "user6@example.com",
      orderDate: "2025-04-23 20:00:00",
      address: "서울특별시 구로구 고척동",
      postCode: "08576",
      items: [
        { name: "Brazil Serra Do Caparaó", quantity: 2 },
        { name: "Ethiopia Yirgacheffe", quantity: 1 }
      ],
      total: 19000
    }
];

let currentEditIndex = null;

// 메뉴 아이템을 localStorage에 저장
function saveMenuItems() {
  localStorage.setItem('menuItems', JSON.stringify(menuItems));
}

// 메뉴 아이템 추가
document.getElementById('add-item-btn').addEventListener('click', function() {
  if (currentEditIndex !== null) {
    alert("수정을 완료해주세요.");
    return;
  }

  const addForm = document.getElementById('add-item-form');
  addForm.style.display = 'block';
  document.getElementById('add-item-btn').style.display = 'none';
});

// 메뉴 아이템 추가 폼 제출
document.getElementById('submit-item-btn').addEventListener('click', function(event) {
  event.preventDefault();

  const name = document.getElementById('item-name').value;
  const price = parseInt(document.getElementById('item-price').value);
  const category = document.getElementById('item-category').value;
  const image = document.getElementById('item-image').files[0];
  const description = document.getElementById('item-description').value;

  if (!name || !price || !category || !description || !image) {
    document.getElementById('form-alert').style.display = 'block';
    return;
  } else {
    document.getElementById('form-alert').style.display = 'none';
  }

  let imageUrl = "";
  if (image) {
    const reader = new FileReader();
    reader.onloadend = function() {
      imageUrl = reader.result;
      const newItem = { name, price, category, image: imageUrl, description };
      menuItems.push(newItem);
      saveMenuItems();
      renderMenuItems();
      clearForm();
    };
    reader.readAsDataURL(image);
  } else {
    const newItem = { name, price, category, image: "", description };
    menuItems.push(newItem);
    saveMenuItems();
    renderMenuItems();
    clearForm();
  }
});

// 메뉴 아이템 렌더링
function renderMenuItems() {
  const menuListElement = document.getElementById('menu-items-list');
  menuListElement.innerHTML = '';

  menuItems.forEach((item, index) => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td><img src="${item.image}" alt="${item.name}"></td>
      <td id="name-${index}" contenteditable="false">${item.name}</td>
      <td id="price-${index}" contenteditable="false">${item.price}원</td>
      <td id="category-${index}" contenteditable="false">${item.category}</td>
      <td id="description-${index}" contenteditable="false">${item.description}</td>
      <td><button class="btn btn-warning btn-sm" onclick="editMenuItem(${index})">수정</button></td>
      <td><button class="btn btn-danger btn-sm" onclick="deleteMenuItem(${index})">삭제</button></td>
    `;
    menuListElement.appendChild(row);
  });
}

// 메뉴 아이템 수정
function editMenuItem(index) {
  if (currentEditIndex !== null) {
    alert("수정을 완료해주세요.");
    return;
  }

  const row = document.getElementById('menu-items-list').children[index];
  row.classList.add('modified-row');

  const nameCell = row.querySelector(`#name-${index}`);
  const priceCell = row.querySelector(`#price-${index}`);
  const categoryCell = row.querySelector(`#category-${index}`);
  const descriptionCell = row.querySelector(`#description-${index}`);

  nameCell.contentEditable = "true";
  priceCell.contentEditable = "true";
  categoryCell.contentEditable = "true";
  descriptionCell.contentEditable = "true";

  const editButton = row.querySelector('button.btn-warning');
  editButton.innerText = "저장";
  editButton.setAttribute('onclick', `saveMenuItem(${index})`);

  currentEditIndex = index;
}

// 메뉴 아이템 저장
function saveMenuItem(index) {
  const row = document.getElementById('menu-items-list').children[index];
  const nameCell = row.querySelector(`#name-${index}`);
  const priceCell = row.querySelector(`#price-${index}`);
  const categoryCell = row.querySelector(`#category-${index}`);
  const descriptionCell = row.querySelector(`#description-${index}`);

  menuItems[index] = {
      name: nameCell.innerText,
      price: parseInt(priceCell.innerText.replace("원", "")),
      category: categoryCell.innerText,
      image: menuItems[index].image,
      description: descriptionCell.innerText
  };

  saveMenuItems();
  renderMenuItems();
  currentEditIndex = null;
}

// 메뉴 아이템 삭제
function deleteMenuItem(index) {
  if (currentEditIndex !== null) {
    alert("수정을 완료해주세요.");
    return;
  }

  if (confirm("정말로 이 메뉴를 삭제하시겠습니까?")) {
    menuItems.splice(index, 1);
    saveMenuItems();
    renderMenuItems();
  }
}

// 메뉴 추가 폼 취소
document.getElementById('cancel-add-item-btn').addEventListener('click', function() {
  clearForm();
});

function clearForm() {
  document.getElementById('item-name').value = '';
  document.getElementById('item-price').value = '';
  document.getElementById('item-category').value = '';
  document.getElementById('item-image').value = '';
  document.getElementById('item-description').value = '';
  document.getElementById('add-item-form').style.display = 'none';
  document.getElementById('add-item-btn').style.display = 'inline-block';
}

// 메뉴 아이템 관리 페이지 표시
document.getElementById('menu-item-link').addEventListener('click', function() {
  if (currentEditIndex !== null) {
    alert("수정을 완료해주세요.");
    return;
  }
  document.getElementById('menu-item-page').style.display = 'block';
  document.getElementById('order-history-page').style.display = 'none';
});

// 주문 내역 페이지 렌더링
function renderOrderHistory() {
  const orderList = document.getElementById('order-list');
  orderList.innerHTML = '';

  orders.forEach(order => {
    let itemsList = '';
    order.items.forEach(item => {
      itemsList += `${item.name} (${item.quantity}개), `;
    });
    itemsList = itemsList.slice(0, -2);

    const orderDate = new Date(order.orderDate);
    const cutoffTime = new Date(order.orderDate);
    cutoffTime.setHours(14, 0, 0, 0);

    const orderTimeClass = orderDate < cutoffTime ? 'before-2pm' : 'after-2pm';

    const formattedOrderDate = orderDate.toISOString().slice(0, 19).replace("T", " ");

    orderList.innerHTML += `
      <div class="order-block ${orderTimeClass}">
        <h5>이메일: ${order.email}</h5>
        <p>주문 일자: ${formattedOrderDate}</p>
        <p>주소: ${order.address}</p>
        <p>우편번호: ${order.postCode}</p>
        <p>상품 내역: ${itemsList}</p>
        <p>총액: ${order.total}원</p>
      </div>
      <hr>
    `;
  });
}

// 주문 내역 페이지 표시
document.getElementById('order-history-link').addEventListener('click', function() {
  document.getElementById('order-history-page').style.display = 'block';
  document.getElementById('menu-item-page').style.display = 'none';
  renderOrderHistory();
});

// 초기 렌더링
renderMenuItems();