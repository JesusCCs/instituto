import * as React from 'react';
import {ActivityIndicator, FlatList, RefreshControl} from 'react-native';
import ItemListado from '../atoms/ItemListado';
import {getBusqueda} from '../../utils/Api';
import Loading from '../atoms/Loading';
import ErrorApi from '../atoms/ErrorApi';

export default function Listado({busqueda, navigation}) {
  const [data, setData] = React.useState([]);
  const [offSet, setOffSet] = React.useState(10);

  const [isLoading, setLoading] = React.useState(true);
  const [refreshing, setRefreshing] = React.useState(false);
  const [refreshIndicator, setRefreshIndicator] = React.useState(false);

  const [maxElements, setMaxElements] = React.useState(0);

  React.useEffect(() => {
    getBusqueda(busqueda).then(({lista, numMax}) => {
      if (!lista) {
        ErrorApi();
      }

      setData(lista);
      setLoading(false);
      setMaxElements(numMax);
    });
  }, [busqueda]);

  const onEndReached = async () => {
    if (refreshing || offSet >= maxElements) {
      return;
    }

    setOffSet(offSet + 10);
    setRefreshing(true);

    getBusqueda(busqueda, offSet).then(({lista}) => {
      if (!lista) {
        ErrorApi();
      }

      if (lista[0].pageid === data[data.length - 1].pageid) {
        lista = lista.shift();
      }

      setData(data.concat(lista));
      setRefreshing(false);
    });
  };

  const onRefresh = async () => {
    setRefreshIndicator(true);
    setOffSet(10);

    getBusqueda(busqueda).then(({lista}) => {
      if (!lista) {
        ErrorApi();
      }

      setData(lista);
      setRefreshIndicator(false);
    });
  };

  const getFooter = () => {
    return refreshing ? (
      <ActivityIndicator
        style={{marginBottom: 20}}
        size={'large'}
        color="#00ff00"
      />
    ) : null;
  };

  return isLoading ? (
    <Loading />
  ) : (
    <FlatList
      data={data}
      keyExtractor={({pageid}) => Math.random() + pageid.toString()}
      initialNumToRender={10}
      onEndReachedThreshold={0.1}
      onEndReached={onEndReached}
      refreshing={refreshing}
      updateCellsBatchingPeriod={30}
      removeClippedSubviews={false}
      refreshControl={
        <RefreshControl refreshing={refreshIndicator} onRefresh={onRefresh} />
      }
      renderItem={({item}) => (
        <ItemListado item={item} navigation={navigation} />
      )}
      ListFooterComponent={getFooter}
    />
  );
}
