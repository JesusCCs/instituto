import * as React from 'react';
import {useWindowDimensions, ScrollView, Alert} from 'react-native';
import HTML from 'react-native-render-html';
import {getPage} from '../utils/Api';
import Loading from '../components/atoms/Loading';

export default function ArticuloScreen({route}) {
  const {pageid} = route.params;
  const contentWidth = useWindowDimensions().width;

  const [data, setData] = React.useState([]);
  const [isLoading, setLoading] = React.useState(true);

  React.useEffect(() => {
    getPage(pageid).then((page) => {
      setData(page);
      setLoading(false);
    });
  }, [pageid]);

  const advertencia = () => {
    Alert.alert(
      'Atención',
      'Esto es solo una vista del documento, los links no son funcionales',
      [{text: 'De acuerdo'}],
    );
  };

  return isLoading ? (
    <Loading />
  ) : (
    <ScrollView style={{padding: 10}}>
      <HTML
        source={{html: data}}
        contentWidth={contentWidth}
        onLinkPress={advertencia}
        baseFontStyle={{fontSize: 16}}
      />
    </ScrollView>
  );
}
