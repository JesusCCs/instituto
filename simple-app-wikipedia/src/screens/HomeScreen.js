import * as React from 'react';
import {Alert, Button, Image, TextInput, View} from 'react-native';
import Listado from '../components/molecules/Listado';
import {styles} from '../styles/style';

export default function HomeScreen({navigation}) {
  const [busqueda, setBusqueda] = React.useState('');

  const submit = () => {
    if (!busqueda.trim().length) {
      Alert.alert('Error', 'La búsqueda no puede ser vacía', [
        {text: 'De acuerdo'},
      ]);
      return;
    }

    navigation.navigate('ListaScreen', {busqueda: busqueda});
  };

  return (
    <View style={styles.container}>
      <View style={styles.separator} />

      <Image
        style={styles.logo}
        source={require('../assets/img/Wikipedia-logo.png')}
      />

      <TextInput
        style={styles.input}
        accessibilityLabel={'input'}
        placeholder={'¿Sobre qué quieres buscar?'}
        onChangeText={(texto) => setBusqueda(texto)}
      />

      <Button
        style={styles.button}
        onPress={submit}
        accessibilityLabel={'submit'}
        title={'Buscar'}
        color={'#282828'}
      />

      <View style={styles.separator} />

      <Listado busqueda={'teletrabajo'} navigation={navigation} />

      <View style={styles.separator} />
    </View>
  );
}
