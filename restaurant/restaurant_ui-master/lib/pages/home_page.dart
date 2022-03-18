import 'package:badges/badges.dart';
import 'package:flutter/material.dart';
import 'package:resturaunt_ui/constants.dart';
import 'package:resturaunt_ui/item_model.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage>
    with SingleTickerProviderStateMixin {
  late TabController _controller;

  @override
  void initState() {
    super.initState();

    _controller = TabController(length: 4, vsync: this);
  }

  @override
  void dispose() {
    super.dispose();

    _controller.dispose();
  }

  List selecteditems = [];

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: Colors.grey[200],
        body: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Align(
                alignment: Alignment.topRight,
                child: Badge(
                  animationDuration: const Duration(milliseconds: 1500),
                  badgeContent: Text(
                    selecteditems.length.toString(),
                    style: const TextStyle(color: Colors.white),
                  ),
                  padding: const EdgeInsets.all(6),
                  badgeColor: Colors.yellow.shade900,
                  child: const Icon(
                    Icons.shopping_bag_rounded,
                    size: 40,
                    color: Colors.black,
                  ),
                ),
              ),
              const SizedBox(
                height: 10,
              ),
              Text(
                'Food Delivery',
                style: style.copyWith(
                    fontSize: 30,
                    fontWeight: FontWeight.w900,
                    color: Colors.black),
              ),
              const SizedBox(
                height: 20,
              ),
              TabBar(
                  automaticIndicatorColorAdjustment: true,
                  indicator: BoxDecoration(
                      borderRadius: BorderRadius.circular(20),
                      color: Colors.yellow.shade900),
                  labelColor: Colors.white,
                  labelPadding: const EdgeInsets.symmetric(horizontal: 10),
                  unselectedLabelColor: Colors.grey.shade700,
                  controller: _controller,
                  labelStyle:
                      style.copyWith(fontSize: 13, fontWeight: FontWeight.w900),
                  tabs: const [
                    Tab(
                      text: 'Burgers',
                    ),
                    Tab(
                      text: 'Pizzas',
                    ),
                    Tab(
                      text: 'Drinks',
                    ),
                    Tab(
                      text: 'Meals',
                    ),
                  ]),
              const SizedBox(
                height: 28,
              ),
              Text(
                'Free Delivery',
                style: style.copyWith(
                    fontSize: 20,
                    fontWeight: FontWeight.w900,
                    color: Colors.grey.shade600),
              ),
              const SizedBox(
                height: 20,
              ),
              Expanded(
                child: TabBarView(
                    physics: const NeverScrollableScrollPhysics(),
                    controller: _controller,
                    children: [
                      _buildlistitem(
                          itemlist: burgeritems, length: burgeritems.length),
                      _buildlistitem(
                          itemlist: pizzaitems, length: pizzaitems.length),
                      _buildlistitem(
                          itemlist: drinksitems, length: drinksitems.length),
                      _buildlistitem(
                          itemlist: mealsitems, length: mealsitems.length),
                    ]),
              ),
              const SizedBox(
                height: 30,
              ),
              Center(
                child: Container(
                  width: double.maxFinite,
                  decoration: BoxDecoration(
                      color: Colors.yellow.shade900,
                      borderRadius: BorderRadius.circular(20)),
                  //padding: const EdgeInsets.symmetric(horizontal: 30),
                  child: MaterialButton(
                    onPressed: () {},
                    child: Text(
                      'Order',
                      style: style.copyWith(fontSize: 22),
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildlistitem(
      {required int length, required List<ItemModel> itemlist}) {
    return ListView.builder(
        physics: const BouncingScrollPhysics(),
        scrollDirection: Axis.horizontal,
        itemCount: length,
        itemBuilder: (_, index) {
          return _buildcard(index: index, myitemlist: itemlist);
        });
  }

  Widget _buildcard({required int index, required List<ItemModel> myitemlist}) {
    return AspectRatio(
      aspectRatio: 2 / 3,
      child: Container(
        margin: const EdgeInsets.only(right: 10),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          image: DecorationImage(
              image: AssetImage(
                myitemlist[index].image,
              ),
              fit: BoxFit.cover),
        ),
        child: Container(
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(20),
            gradient: LinearGradient(
                colors: [
                  Colors.black.withOpacity(.8),
                  Colors.black.withOpacity(.2),
                ],
                begin: Alignment.bottomCenter,
                stops: const [.2, .6]),
          ),
          child: Padding(
            padding: const EdgeInsets.all(20.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                Align(
                  alignment: Alignment.topRight,
                  child: GestureDetector(
                    onTap: () {
                      setState(() {
                        myitemlist[index].isselected =
                            !myitemlist[index].isselected;
                        if (myitemlist[index].isselected) {
                          selecteditems.add(myitemlist[index].isselected);
                        } else {
                          selecteditems.removeAt(index);
                        }
                      });
                    },
                    child: Icon(
                      Icons.favorite,
                      color: myitemlist[index].isselected
                          ? Colors.red
                          : Colors.white,
                      size: 30,
                    ),
                  ),
                ),
                const Spacer(),
                Text(
                  '\$ ${myitemlist[index].price}',
                  style: style.copyWith(
                      fontSize: 28,
                      fontWeight: FontWeight.w700,
                      color: Colors.white),
                ),
                const SizedBox(
                  height: 10,
                ),
                Text(
                  myitemlist[index].title,
                  style: style.copyWith(
                      fontSize: 18,
                      fontWeight: FontWeight.w900,
                      color: Colors.white),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
