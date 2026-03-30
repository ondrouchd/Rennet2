import 'package:flutter/material.dart';

void main() {
  runApp(const RennetApp());
}

class RennetApp extends StatelessWidget {
  const RennetApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Rennet2 – Sýrařův rádce',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.amber),
        useMaterial3: true,
      ),
      home: const HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text('Rennet2 – Sýrařův rádce'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Icon(
              Icons.set_meal,
              size: 80,
              color: Colors.amber,
            ),
            const SizedBox(height: 24),
            Text(
              'Vítejte v Sýrařově rádci',
              style: Theme.of(context).textTheme.headlineSmall,
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 16),
            Text(
              'Váš průvodce výrobou sýra',
              style: Theme.of(context).textTheme.bodyLarge,
              textAlign: TextAlign.center,
            ),
          ],
        ),
      ),
    );
  }
}
