import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:resturaunt_ui/item_model.dart';

final style = GoogleFonts.roboto(
  fontSize: 30,
  color: Colors.white,
);

final List<ItemModel> burgeritems = [
  ItemModel(
      image: 'assets/humburgerone.jpg',
      title: 'Humburger 1',
      price: 12.00,
      isselected: false),
  ItemModel(
      image: 'assets/humburgertwo.jpg',
      title: 'Humburger 2',
      price: 08.00,
      isselected: false),
  ItemModel(
      image: 'assets/humburgerthree.jpg',
      title: 'Humburger 3',
      price: 12.00,
      isselected: false),
];

final List<ItemModel> pizzaitems = [
  ItemModel(
      image: 'assets/pizzaone.jpg',
      title: 'Pizza 1',
      price: 24.00,
      isselected: false),
  ItemModel(
      image: 'assets/pizzatwo.jpg',
      title: 'Pizza 2',
      price: 21.00,
      isselected: false),
  ItemModel(
      image: 'assets/pizzathree.jpg',
      title: 'Pizza 3',
      price: 40.00,
      isselected: false),
];

final List<ItemModel> drinksitems = [
  ItemModel(
      image: 'assets/coffee.jpg',
      title: 'Coffee',
      price: 08.00,
      isselected: false),
  ItemModel(
      image: 'assets/milk.jpg', title: 'Milk', price: 11.00, isselected: false),
];
final List<ItemModel> mealsitems = [
  ItemModel(
      image: 'assets/puncake.jpg',
      title: 'Puncake',
      price: 33.00,
      isselected: false),
  ItemModel(
      image: 'assets/dessert.jpg',
      title: 'Dessert 1',
      price: 28.00,
      isselected: false),
  ItemModel(
      image: 'assets/dessert2.jpg',
      title: 'Dessert 2',
      price: 30.00,
      isselected: false),
];
