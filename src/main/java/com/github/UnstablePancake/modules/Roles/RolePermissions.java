package com.github.UnstablePancake.modules.Roles;
import sx.blah.discord.handle.obj.Permissions;
import java.util.EnumSet;

public class RolePermissions {

    public static EnumSet<Permissions> administrator = EnumSet.of(Permissions.ADMINISTRATOR);
    public static EnumSet<Permissions> moderator = EnumSet.of(Permissions.MANAGE_CHANNELS, Permissions.KICK, Permissions.CREATE_INVITE, Permissions.CHANGE_NICKNAME, Permissions.MANAGE_NICKNAMES, Permissions.USE_EXTERNAL_EMOJIS, Permissions.READ_MESSAGES, Permissions.SEND_MESSAGES, Permissions.SEND_TTS_MESSAGES, Permissions.MANAGE_MESSAGES, Permissions.EMBED_LINKS, Permissions.ATTACH_FILES, Permissions.READ_MESSAGE_HISTORY, Permissions.MENTION_EVERYONE, Permissions.USE_EXTERNAL_EMOJIS, Permissions.ADD_REACTIONS, Permissions.VOICE_CONNECT, Permissions.VOICE_SPEAK, Permissions.VOICE_MUTE_MEMBERS, Permissions.VOICE_DEAFEN_MEMBERS, Permissions.VOICE_USE_VAD);
    public static EnumSet<Permissions> regular = EnumSet.of(Permissions.CREATE_INVITE, Permissions.CHANGE_NICKNAME, Permissions.USE_EXTERNAL_EMOJIS, Permissions.READ_MESSAGES, Permissions.SEND_MESSAGES, Permissions.SEND_TTS_MESSAGES, Permissions.EMBED_LINKS, Permissions.ATTACH_FILES, Permissions.READ_MESSAGE_HISTORY, Permissions.MENTION_EVERYONE, Permissions.USE_EXTERNAL_EMOJIS, Permissions.ADD_REACTIONS, Permissions.VOICE_CONNECT, Permissions.VOICE_SPEAK, Permissions.VOICE_USE_VAD);
    public static EnumSet<Permissions> user = EnumSet.of(Permissions.CREATE_INVITE, Permissions.CHANGE_NICKNAME, Permissions.USE_EXTERNAL_EMOJIS, Permissions.READ_MESSAGES, Permissions.SEND_MESSAGES, Permissions.SEND_TTS_MESSAGES, Permissions.EMBED_LINKS, Permissions.ATTACH_FILES, Permissions.READ_MESSAGE_HISTORY, Permissions.MENTION_EVERYONE, Permissions.USE_EXTERNAL_EMOJIS, Permissions.ADD_REACTIONS, Permissions.VOICE_CONNECT, Permissions.VOICE_SPEAK, Permissions.VOICE_USE_VAD);
    public static EnumSet<Permissions> guest = EnumSet.of(Permissions.READ_MESSAGES, Permissions.SEND_MESSAGES, Permissions.EMBED_LINKS, Permissions.ATTACH_FILES, Permissions.USE_EXTERNAL_EMOJIS, Permissions.ADD_REACTIONS, Permissions.VOICE_CONNECT, Permissions.VOICE_SPEAK, Permissions.VOICE_USE_VAD);
}
