<%@page import="elements.Qualite"%>
<%@page import="java.util.List"%>
<%@page import="elements.Route"%>
<%@page import="elements.Pourcentage"%>

<%
                Route appelRoute = new Route();
                List<Route> route = appelRoute.SelectRoute();
                Qualite appelQualite = new Qualite();
                List<Qualite> ListeQualite = appelQualite.SelectQualite();
            %>

<%if(request.getParameter("idproduit") != null && request.getParameter("taille") != null){%>
            <% Pourcentage appel = new Pourcentage();
                Pourcentage prt = appel.AvoirPourcentage(request.getParameter("idproduit"), request.getParameter("taille"));
                
                
            %>
                <script>
                    document.addEventListener("DOMContentLoaded", () => {
                        // Initialisez le graphique avec des données initiales
                        const chart = new ApexCharts(document.querySelector("#pieChart"), {
                            series: [<%= prt.getNombrePrive() %>, <%= prt.getNombrePublic() %>],  // Initialisation avec les pourcentages privés et publics
                            chart: {
                                height: 350,
                                type: 'pie',
                                toolbar: {
                                    show: true
                                }
                            },
                            labels: ['Privee', 'Public']
                        });

                        // Rendre le graphique
                        chart.render();

                        // Fonction pour mettre à jour les données du graphique
                        function updateChartData(privatePercentage, publicPercentage) {
                            chart.updateSeries([privatePercentage, publicPercentage]);
                        }

                        // Exemple d'utilisation : Mettez à jour les données du graphique avec de nouvelles valeurs
                        updateChartData(<%= prt.getNombrePrive() %>, <%= prt.getNombrePublic() %>); // Mettez à jour avec les nouveaux pourcentages

                        // Vous pouvez appeler cette fonction avec de nouvelles valeurs lorsque vous en avez besoin
                    });
                </script>
            <!-- End Pie Chart -->
                    <div class="mb-3">
                    <p>Privee :<%= prt.getNombrePrive() %>%</p>
                  </div>
                  
                  <div class="mb-3">
                    <p>Public : <%= prt.getNombrePublic() %>%</p>
                  </div>