import * as React from 'react';
import Listado from '../components/molecules/Listado';

export default function ListaScreen({route, navigation}) {
  const {busqueda} = route.params;

  return <Listado busqueda={busqueda} navigation={navigation} />;
}
