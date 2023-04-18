import expect from "expect";
import { isProductInCart } from "../../src/cart";

const scenarios = [
  [
    {
      id: "1",
      title: "Product 1",
      description: "product 1 description",
      stock: 1,
      price: 5099,
      thumbnail:
        "https://c1.neweggimages.com/ProductImageCompressAll1280/32-116-471-01.jpg",
    },
    false,
  ],
  [
    {
      id: "2",
      title: "Product with null",
      description: null,
      stock: 4,
      price: 3214,
      thumbnail:
        "https://c1.neweggimages.com/ProductImageCompressAll1280/19-118-412-V01.jpg",
    },
    true,
  ],
];

test.each(scenarios)("isProductInCartTest", (input, expected) => {
  const product = input;
  const items = [
    {
      quantity: 12,
      product: {
        id: "2",
        title: "Product with null",
        description: null,
        stock: 4,
        price: 3214,
        thumbnail:
          "https://c1.neweggimages.com/ProductImageCompressAll1280/19-118-412-V01.jpg",
      },
    },
    {
      quantity: 10,
      product: {
        id: "3",
        title: "Product with html tag",
        description: "<h2>hack attempt<h2>",
        stock: 8,
        price: 112,
        thumbnail:
          "https://c1.neweggimages.com/ProductImageCompressAll1280/B8EHS2201201D5Q00BF.jpg",
      },
    },
    {
      quantity: 10,
      product: {
        id: "4",
        title: "Product with 'Backspace Character'",
        description: "(U+2408)",
        stock: 10,
        price: 9797,
        thumbnail: null,
      },
    },
    {
      quantity: 3,
      product: {
        id: "5",
        title: "Product with word that is 256 characters in length",
        description:
          "kjhbafsbkjknbfjasbkjsfabkjasfbkjfasbkjhbkjhsafbkjhsfabkjhbkjsfakbjfsabkjsfabkjbkjfsabkjfsajbjk.fbkjsabjk.fkjasbfkjbasjbf.jkasjkfbjkas.jkbf.bjkasb.jkf.jkabsfbkj.asbfjkbasjkbfjksabjkbfjkasbfj.kbasj.kbfjkabsfjkbaskjkjf.ajksbafjkbbsajsbfkabasfkj.bsajkbafs.kbkj",
        stock: 3,
        price: 6441,
        thumbnail:
          "https://c1.neweggimages.com/ProductImageCompressAll1280/20-331-417-V05.jpg",
      },
    },
    {
      quantity: 0,
      product: {
        id: "6",
        title: "Product with SQL code",
        description: "SELECT * FROM Users WHERE UserId = 2",
        stock: 0,
        price: 1644,
        thumbnail:
          "https://c1.neweggimages.com/ProductImageCompressAll1280/34-236-233-V21.jpg",
      },
    },
  ];
  const actual = isProductInCart(product, items);
  expect(actual).toEqual(expected);
});
