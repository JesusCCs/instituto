# -*- coding: utf-8 -*-
{
    'name': "Viajes",

    'summary': """
        Organiza viajes a lugares lejanos""",

    'description': """
        No todo es quedarse en la Tierra
    """,

    'author': "Jesús Cañas",
    'website': "https://ciclo.iespablopicasso.es/",

    # Categories can be used to filter modules in modules listing
    # Check https://github.com/odoo/odoo/blob/14.0/odoo/addons/base/data/ir_module_category_data.xml
    # for the full list
    'category': 'Services',
    'version': '0.1',

    # any module necessary for this one to work correctly
    'depends': ['base'],

    # always loaded
    'data': [
        'security/security.xml',
        'security/ir.model.access.csv',
        'views/templates.xml',
        'views/res_planetas_views.xml',
        'views/res_rutas_views.xml',
        'views/res_viajes_views.xml',
        'views/res_naves_views.xml',
        'views/views.xml',
        'reports/nave.xml',
        'reports/planeta.xml',
        'reports/ruta.xml',
        'reports/viaje.xml',
    ],
    # only loaded in demonstration mode
    'demo': [
        'demo/demo.xml',
    ],
    'installable': True,
    'application': True,
}
