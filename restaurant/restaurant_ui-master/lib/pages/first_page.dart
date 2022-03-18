import 'package:animate_do/animate_do.dart';
import 'package:flutter/material.dart';
import 'package:page_transition/page_transition.dart';
import 'package:resturaunt_ui/constants.dart';
import 'package:resturaunt_ui/pages/home_page.dart';

class FirstPage extends StatefulWidget {
  const FirstPage({Key? key}) : super(key: key);

  @override
  _FirstPageState createState() => _FirstPageState();
}

class _FirstPageState extends State<FirstPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: const BoxDecoration(
            image: DecorationImage(
                image: AssetImage('assets/one.jpg'), fit: BoxFit.cover)),
        child: Container(
          decoration: BoxDecoration(
              gradient: LinearGradient(
            colors: [
              Colors.black.withOpacity(.9),
              Colors.black.withOpacity(.8),
              Colors.black.withOpacity(.2),
            ],
            begin: Alignment.bottomCenter,
          )),
          child: Padding(
            padding: const EdgeInsets.all(20),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.end,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                FadeInRight(
                  duration: const Duration(seconds: 2),
                  child: Text(
                    'Taking Order\nFor Faster Delivery',
                    style: style.copyWith(
                        fontSize: 40, fontWeight: FontWeight.bold, height: 1.3),
                  ),
                ),
                const SizedBox(
                  height: 12,
                ),
                FadeInRight(
                  duration: const Duration(seconds: 2),
                  child: Text(
                    'See resturants nearby by\nadding your location ',
                    style: style.copyWith(
                        height: 1.7,
                        fontSize: 18,
                        letterSpacing: 1.4,
                        color: Colors.grey),
                  ),
                ),
                const SizedBox(
                  height: 80.0,
                ),
                FadeInRight(
                  duration: const Duration(seconds: 2),
                  child: Container(
                      width: double.infinity,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(10),
                        gradient: const LinearGradient(
                            colors: [Colors.orange, Colors.yellow],
                            begin: Alignment.centerLeft),
                      ),
                      child: MaterialButton(
                        onPressed: () {
                          Navigator.push(
                              context,
                              PageTransition(
                                  child: const HomePage(),
                                  duration: const Duration(seconds: 2),
                                  type: PageTransitionType.rightToLeft));
                        },
                        child: Text(
                          'Start',
                          style: style,
                        ),
                        padding: const EdgeInsets.symmetric(vertical: 10),
                      )),
                ),
                const SizedBox(
                  height: 20,
                ),
                FadeInRight(
                  duration: const Duration(seconds: 2),
                  child: Center(
                    child: Text(
                      'Now Deliver to your door 24\\7 ',
                      style: style.copyWith(fontSize: 13, color: Colors.grey),
                    ),
                  ),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
