import * as React from 'react';
import {Alert} from 'react-native';

export default function ErrorApi() {
  return Alert.alert(
    'Error',
    'Hubo un problema a la hora de acceder a la API',
    [{text: 'De acuerdo'}],
  );
}
