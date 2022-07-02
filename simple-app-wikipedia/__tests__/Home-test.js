// @flow
import * as React from 'react';
import {render, fireEvent} from '@testing-library/react-native';
import HomeScreen from '../src/screens/HomeScreen';

describe('Testeo de la navegación', () => {
  // test('clicar en buscar te lleva a la lista', async () => {
  //   const component = (
  //     <NavigationContainer>
  //       <Navigation />
  //     </NavigationContainer>
  //   );
  //
  //   const {getByA11yLabel, getByText} = render(component);
  //
  //   const input = getByA11yLabel('input');
  //   fireEvent.changeText(input, 'hola');
  //
  //   const toClick = await getByA11yLabel('submit');
  //   fireEvent.press(toClick);
  //
  //   const newHeader = await getByText("Resultados para 'hola'");
  //
  //   expect(newHeader).toBeTruthy();
  // });

  it('navegación a la lista', () => {
    const navigation = jest.fn();

    const {getByA11yLabel} = render(<HomeScreen navigation={navigation} />);
    fireEvent.press(getByA11yLabel('submit'));

    expect(navigation).toHaveBeenCalledWith('ListaScreen');
  });
});
