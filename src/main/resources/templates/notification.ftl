<#ftl encoding="UTF-8">
<#switch event.type>
        <#case "deviceOnline">
                A ${device.name} kapcsolódott a szerverhez.
                <#break>
        <#case "deviceOffline">
                A ${device.name} kapcsolatot bontott a szerverrel.
                <#break>
        <#case "deviceUnknown">
                A ${device.name} egy ideje nem elérhető.
                <#break>

        <#case "deviceMoving">
                A ${device.name} mozgásban van.
                <#break>
        <#case "deviceStopped">
                A ${device.name} megállt.
                <#break>

        <#case "geofenceEnter">
                A ${device.name} a geokerítésen belülre lépett.
                <#break>
        <#case "geofenceExit">
                A ${device.name} a geokerítésen kívülre lépett.
                <#break>

        <#case "alarm">
                A ${device.name} riasztást küldött.
                <#break>

        <#default>
                Ismeretlen esemény típus: ${event.type}.
</#switch>