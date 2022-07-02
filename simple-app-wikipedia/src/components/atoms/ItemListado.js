import * as React from 'react';
import {Text, TouchableOpacity, useWindowDimensions} from 'react-native';
import HTML from 'react-native-render-html';
import {styles} from '../../styles/style';

function ItemListado({item, navigation}) {
  const contentWidth = useWindowDimensions().width;
  const snippet = `...${item.snippet}...`;

  return (
    <TouchableOpacity
      style={styles.item}
      onPress={() =>
        navigation.navigate('ArticuloScreen', {
          pageid: item.pageid,
          title: item.title,
        })
      }>
      <Text style={styles.title}>{item.title}</Text>
      <HTML
        source={{html: snippet}}
        contentWidth={contentWidth}
        containerStyle={styles.body}
        {...{
          classesStyles: {
            searchmatch: {color: 'green'},
          },
        }}
      />
    </TouchableOpacity>
  );
}

export default React.memo(ItemListado);
