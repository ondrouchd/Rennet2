import 'package:flutter_test/flutter_test.dart';

import 'package:rennet2/main.dart';

void main() {
  testWidgets('HomePage renders correctly', (WidgetTester tester) async {
    await tester.pumpWidget(const RennetApp());

    expect(find.text('Rennet2 – Sýrařův rádce'), findsOneWidget);
    expect(find.text('Vítejte v Sýrařově rádci'), findsOneWidget);
  });
}
