/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */
import 'react-native-gesture-handler';
import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import HomeScreen from './screens/HomeScreen';
import ListaScreen from './screens/ListaScreen';
import ArticuloScreen from './screens/ArticuloScreen';

const Stack = createStackNavigator();

const Navigation = () => {
  return (
    <Stack.Navigator screenOptions={{headerTitleAlign: 'center'}}>
      <Stack.Screen
        name="HomeScreen"
        component={HomeScreen}
        options={{headerShown: false}}
      />
      <Stack.Screen
        name="ListaScreen"
        component={ListaScreen}
        options={({route}) => ({
          title: `Resultados para '${route.params.busqueda}'`,
        })}
      />
      <Stack.Screen
        name="ArticuloScreen"
        component={ArticuloScreen}
        options={({route}) => ({
          title: route.params.title,
        })}
      />
    </Stack.Navigator>
  );
};

export default Navigation;
